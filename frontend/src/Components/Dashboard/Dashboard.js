import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Card} from "@material-ui/core";
import CardHeader from "@material-ui/core/CardHeader";
import Typography from "@material-ui/core/Typography";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Container from "@material-ui/core/Container";
import HorizontalList, {DimmedExpandableCard} from "../UI/HorizontalList/HorizontalList";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      randomRecipes: []
    }
  }
  componentDidMount() {
    const { setIsReady } = this.props;
    setIsReady(false);
    const apiKey = 'c79c6bea99154e7da483a920455b2d77';
    const url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    fetch(url, { method: 'GET' })
      .then(res => res.json())
      .then(randomRecipes => {
        this.setState({ randomRecipes: randomRecipes.recipes })
        console.log(randomRecipes.recipes)
      })
      .then(() => setIsReady(true))
      .catch(err => err.message)
  }

  render() {
    const { randomRecipes } = this.state;
    return (
      <HorizontalList>
        {
          randomRecipes.map((recipe, index) => (
            <DimmedExpandableCard
              key={index}
              title={recipe.title}
              image={recipe.image}
              supportingText={recipe.summary
                .substr(0, 100)
                .replace(/<.*>/g, '') + '...'}
            />
          ))
        }
      </HorizontalList>
    );
  }
}

Dashboard.propTypes = {};

export default Dashboard;
