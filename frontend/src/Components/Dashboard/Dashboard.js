import React, { Component } from 'react';
import
HorizontalList,
{ DimmedExpandableCard } from '../UI/HorizontalList/HorizontalList';
import { PropTypes } from 'prop-types';
import { increaseLoading, decreaseLoading } from '../../actions/loading';
import { connect } from 'react-redux';
import UserCreation from './UserCreation/UserCreation';
import apiKeys from '../../helpers/spoonaculars';

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      recommendedRecipes: [],
      recentRecipes: [],
      randomRecsRecipes: [],
      usersRecipes: []
    };
  }
  componentDidMount() {
    const { increaseLoading, decreaseLoading, authToken } = this.props;
    increaseLoading();
    // const apiKey = process.env.REACT_APP_SPOONACULAR_API_KEY;
    if (!localStorage.getItem('validApiKeyIndex')) {
      localStorage.setItem('validApiKeyIndex', '0');
    }
    const apiKey = apiKeys[localStorage.getItem('validApiKeyIndex')];
    let url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    const fetchExternalRecipes = (url, attempt) => {
      fetch(url, { method: 'GET' })
          .then((res) => {
            if (res.status === 402) {
              const index =
                (parseInt(localStorage.getItem('validApiKeyIndex')) + 1) % apiKeys.length;
              localStorage.setItem('validApiKeyIndex', index.toString());
              const url =
                'https://api.spoonacular.com/recipes/random?apiKey=' +
                apiKeys[index] + '&number=10&';
              console.error('Api key expired. Try again (',
                  (Math.abs(attempt-apiKeys.length)).toString(), '/'
                  , apiKeys.length.toString() + ')');
              if (!attempt) {
                throw new Error('All api keys expired.');
              }
              return fetchExternalRecipes(url, attempt - 1);
            }
            return res.json();
          })
          .then((randomRecipes) => {
            this.setState({ recommendedRecipes: randomRecipes.recipes });
            console.log(randomRecipes.recipes);
          })
          .catch((err) => {
            console.error('Spoonacular API error: ', err.message);
          })
          .finally(() => decreaseLoading());
    };

    fetchExternalRecipes(url, apiKeys.length);

    // Read recent from local storage and set to state
    // After that, ask API if it's lacking some recipes,
    // if yes, update and re-render :)
    this.setState({ recentRecipes: [] });

    // Show something completely new, be spontaneous
    this.setState({ randomRecsRecipes: [] });

    // Get current user's recipes
    url = '/api/recipes';
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + authToken
    };
    fetch(url, {
      headers
    }).then((res) => res.json())
        .then((usersRecipes) => {
          console.log(usersRecipes.content);
          this.setState({ usersRecipes: usersRecipes.content });
        }).catch((err) => {
          console.error('Error while getting user recipes: ', err.message);
        });
  }

  render() {
    const { recommendedRecipes, randomRecsRecipes, usersRecipes } = this.state;
    const { username } = this.props;
    return (
      <>
        <HorizontalList
          title="Recommended"
        >
          {
            recommendedRecipes.map((recipe, index) => (
              <DimmedExpandableCard
                recipe={recipe}
                key={index}
                index={index}
                title={recipe.title}
                image={recipe.image}
                supportingText={recipe.summary}
                ingredients={recipe.extendedIngredients}
              />
            ))
          }
        </HorizontalList>
        <HorizontalList
          title={'Our recipes'}
        >
          {
            usersRecipes?.map((recipe, index) => (
              <DimmedExpandableCard
                external={false}
                recipe={recipe}
                key={index}
                index={index}
                title={recipe.name}
                image={recipe.photos[0]}
                supportingText={recipe.description}
                ingredients={recipe.ingredients}
              />
            ))
          }
        </HorizontalList>
        <UserCreation />
      </>
    );
  }
}

Dashboard.propTypes = {
  increaseLoading: PropTypes.func,
  decreaseLoading: PropTypes.func,
  username: PropTypes.string,
  authToken: PropTypes.string
};

const mapStateToProps = (state) => ({
  authToken: state.userSession.authToken,
  username: state.userSession.username
});

export default connect(mapStateToProps, { increaseLoading, decreaseLoading })(Dashboard);
