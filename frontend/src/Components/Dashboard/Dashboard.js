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
      randomRecsRecipes: []
    };
  }
  componentDidMount() {
    const { increaseLoading, decreaseLoading } = this.props;
    increaseLoading();
    const apiKey = process.env.REACT_APP_SPOONACULAR_API_KEY;
    const url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    fetch(url, { method: 'GET' })
        .then((res) => res.json())
        .then((randomRecipes) => {
          this.setState({ recommendedRecipes: randomRecipes.recipes });
          console.log(randomRecipes.recipes);
        })
        .catch((err) => err.message)
        .finally(() => decreaseLoading());

    // Read recent from local storage and set to state
    // After that, ask API if it lacking some recipe,
    // if yes, update and rerender :)
    this.setState({ recentRecipes: [] });

    // Show something completely new, be spontaneous
    this.setState({ randomRecsRecipes: [] });
  }

  render() {
    const { recommendedRecipes, recentRecipes, randomRecsRecipes } = this.state;
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
          title="Recently viewed"
        >
          {
            recentRecipes.map((recipe, index) => (
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
  decreaseLoading: PropTypes.func
};

export default connect(null, { increaseLoading, decreaseLoading })(Dashboard);
