import React, { Component } from 'react';
import
HorizontalList,
{ DimmedExpandableCard } from '../UI/HorizontalList/HorizontalList';
import { PropTypes } from 'prop-types';
import { increaseLoading, decreaseLoading } from '../../actions/loading';
import { connect } from 'react-redux';
import UserCreation from './UserCreation/UserCreation';

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
    const apiKey = '3b2eff55d72c4e59b6ca95f82dceaacd';
    console.log(process?.env.REACT_APP_SPOONACULAR_API_KEY);
    let url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    fetch(url, { method: 'GET' })
        .then((res) => res.json())
        .then((randomRecipes) => {
          this.setState({ recommendedRecipes: randomRecipes.recipes });
          console.log(randomRecipes.recipes);
        })
        .catch((err) => err.message)
        .finally(() => decreaseLoading());

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
    const { recommendedRecipes, recentRecipes, randomRecsRecipes, usersRecipes } = this.state;
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
          title={username &&
          username[0].toUpperCase() +
          username.substr(1) +
          '\'s recipes'}
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
        <HorizontalList
          title="Try something new"
        >
          {
            randomRecsRecipes.map((recipe, index) => (
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
        <UserCreation />
      </>
    );
  }
}

Dashboard.propTypes = {
  increaseLoading: PropTypes.func,
  decreaseLoading: PropTypes.func,
  username: PropTypes.string
};

const mapStateToProps = (state) => ({
  authToken: state.userSession.authToken,
  username: state.userSession.username
});

export default connect(mapStateToProps, { increaseLoading, decreaseLoading })(Dashboard);
