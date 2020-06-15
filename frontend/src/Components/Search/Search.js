import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import Paper from '@material-ui/core/Paper';
import { DimmedExpandableCard } from '../UI/HorizontalList/HorizontalList';
import apiKeys from '../../helpers/spoonaculars';
import UnifiedRecipe from '../../helpers/UnifiedRecipe';

/* eslint-disable no-invalid-this */
class Search extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      searchResults: [[], []],
      recipes: []
    };
  }

fetchExternalRecipes = (url, attempt, isExternal, recipeID) => {
  fetch(url, { method: 'GET' })
      .then((res) => {
        if (res.status === 402) {
          const index =
          (parseInt(localStorage.getItem('validApiKeyIndex')) + 1) %
          apiKeys.length;
          localStorage.setItem('validApiKeyIndex', index.toString());
          const url = isExternal ?
          ('https://api.spoonacular.com/recipes/' +
            recipeID + '/information?apiKey=' + apiKeys[index]):
          ('/api/recipes/' + recipeID);
          console.error('Api key expired. Try again (',
              (Math.abs(attempt-apiKeys.length)).toString(), '/'
              , apiKeys.length.toString() + ')');
          if (!attempt) {
            throw new Error('All api keys expired.');
          }
          return this.fetchExternalRecipes(url, attempt - 1);
        }
        return res.json();
      })
      .then((recipe) => {
        console.log(recipe);
        const { recipes } = this.state;
        if (!('ifexternal' in recipe)) {
          const r = new UnifiedRecipe(recipe);
          return this.setState({ recipes: [...recipes, r] });
        }
        this.setState({ recipes: [
          ...recipes,
          { ...recipe,
            readyInMinutes: recipe.steps.reduce((i, j) => i.time + j.time) }
        ] });
      })
      .catch((err) => {
        console.error('Spoonacular API error: ', err);
        this.setState({ error: err.message });
      });
};

componentDidMount() {
  const { search } = this.props.location;
  const searchParams = new URLSearchParams(search);
  const name = searchParams.get('name');
  this.setState({ name });
  fetch('/api/search', {
    headers: { name }
  }).then((res) => {
    if (!res.ok) throw new Error(res.statusText);
    return res.json();
  }).then((searchResults) => {
    console.log(searchResults);
    this.setState({ searchResults: [
      searchResults[0].map((sr) => JSON.parse(sr)),
      searchResults[1]
    ] }, () => {
      const { searchResults } = this.state;
      searchResults.forEach((sr) => {
        sr.forEach((s) => {
          if (!s) return;
          if (s.results && s.results[0]) {
            const recipeID = s.results[0].id;
            console.log(recipeID);
            if (!localStorage.getItem('validApiKeyIndex')) {
              localStorage.setItem('validApiKeyIndex', '0');
            }
            const apiKey = apiKeys[localStorage.getItem('validApiKeyIndex')];
            const url =
              'https://api.spoonacular.com/recipes/' +
              recipeID + '/information?apiKey=' + apiKey;
            this.fetchExternalRecipes(
                url, apiKeys.length, 'true', recipeID);
          } else if (s.recipeid) {
            fetch('/api/recipes/' + s.recipeid)
                .then((res) => {
                  if (!res.ok) throw new Error(res.statusText);
                  return res.json();
                }).then((recipe) => {
                  this.setState({ recipes: [
                    recipe,
                    ...this.state.recipes
                  ] });
                }).catch((err) => {
                  console.error(err);
                });
          }
        });
      });
    });
  }).catch((err) => {
    console.error(err.message);
  });
}

render() {
  const { name, recipes } = this.state;
  return (
    <div>
      <div className='shopping-lists'>
        <Paper
          style={{
            padding: '30px',
            maxWidth: '960px',
            overflow: 'hidden'
          }}
        >
          <h1>
            {'Search results for "' + name + '"'}
          </h1>
          <div className="shopping-lists-wrapper">
            {
               recipes?.map((recipe, index) =>
                 <DimmedExpandableCard
                   external={!recipe.ifexternal}
                   recipe={recipe}
                   key={index}
                   index={index}
                   title={recipe.name}
                   image={recipe.photos[0]}
                   supportingText={recipe.description}
                   ingredients={recipe.extendedIngredients || recipe.ingredients}
                 />)
            }
          </div>
        </Paper>
      </div>
    </div>
  );
}
}

Search.propTypes = {
  location: PropTypes.object
};

export default withRouter(Search);
