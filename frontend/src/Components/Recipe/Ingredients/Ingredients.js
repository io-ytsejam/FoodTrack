import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './Ingredients.sass';
import Chip from '@material-ui/core/Chip';
import Avatar from '@material-ui/core/Avatar';
import Paper from '@material-ui/core/Paper';
import Tooltip from '@material-ui/core/Tooltip';

class Ingredients extends Component {
  componentDidUpdate = () => {

  }

  renderIngredients = (recipe) =>
    recipe
      ?.ingredients
      ?.map((ing) => {
        const src = ing.image ?
          `https://spoonacular.com/cdn/ingredients_100x100/${ing.image}` : undefined;
        return <Chip
          style={{
            margin: '5px',
            backgroundColor: 'transparent',
            boxShadow: '0 0 11px -5px black'
          }}
          variant='outlined'
          color="#ffc310"
          label={ing.name}
          avatar={
            src ? <Tooltip title={ing.name}>
              <Avatar label={ing.name} src={src} />
            </Tooltip> : undefined
          }
        />;
      })

  render() {
    const { recipe } = this.props;
    return (
      <Paper
        style={{
          marginTop: '10px',
          padding: '10px',
          display: 'flex',
          flexWrap: 'wrap',
          justifyContent: 'end'
        }}
      >
        {this.renderIngredients(recipe)}
      </Paper>
    );
  }
}

Ingredients.propTypes = {
  recipe: PropTypes.object
};

export default Ingredients;

class Ingredient extends Component {
  render() {
    return (
      <div>

      </div>
    );
  }
}

Ingredient.propTypes = {

};
