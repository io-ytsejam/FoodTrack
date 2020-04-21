import React, { Component } from 'react';
import
HorizontalList,
{ DimmedExpandableCard } from '../UI/HorizontalList/HorizontalList';
import { PropTypes } from 'prop-types';

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      randomRecipes: []
    };
  }
  componentDidMount() {
    const { setIsReady } = this.props;
    setIsReady(false);
    const apiKey = process.env.REACT_APP_SPOONACULAR_API_KEY;
    const url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    fetch(url, { method: 'GET' })
        .then((res) => res.json())
        .then((randomRecipes) => {
          this.setState({ randomRecipes: randomRecipes.recipes });
          console.log(randomRecipes.recipes);
        })
        .then(() => setIsReady(true))
        .catch((err) => err.message);
  }

  render() {
    const { randomRecipes } = this.state;
    return (
      <HorizontalList>
        {
          randomRecipes.map((recipe, index) => (
            <DimmedExpandableCard
              recipe={recipe}
              key={index}
              title={recipe.title}
              image={recipe.image}
              supportingText={recipe.summary}
              ingredients={recipe.extendedIngredients}
            />
          ))
        }
      </HorizontalList>
    );
  }
}

Dashboard.propTypes = {
  setIsReady: PropTypes.func
};

export default Dashboard;
