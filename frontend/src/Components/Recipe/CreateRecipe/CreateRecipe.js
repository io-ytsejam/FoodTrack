import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './index.sass';
import { TextField } from '@material-ui/core';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import RecipePhotoThumbnail from './RecipePhotoThumbnail';
import Step from './Step';
import Ingredient from './Ingredient';

/* eslint-disable no-invalid-this */
class CreateRecipe extends Component {
  constructor(props) {
    super(props);
    this.state = {
      recipe: {
        name: '',
        description: '',
        ifexternal: 'f',
        ingredients: [{ name: '' }],
        steps: [{ stepdescription: '', time: undefined }],
        photos: [{ photoLink: '' }]
      },
      requestStatus: 'create recipe'
    };
  }

  addOrRemoveIngredient = (key) => (index) => {
    const { recipe } = this.state;
    const { ingredients } = this.state.recipe;
    if (key) {
      console.log(index, ingredients.filter((_x, i) => i !== index));
      this.setState({
        recipe: {
          ...recipe,
          ingredients: ingredients.filter((_x, i) => i !== index)
        }
      });
    } else {
      this.setState({
        recipe: {
          ...recipe,
          ingredients: [
            ...ingredients,
            { name: '' }
          ]
        }
      });
    }
  }

  addOrRemoveStep = (key) => (index) => {
    const { recipe } = this.state;
    const { steps } = recipe;
    if (key) {
      console.log(index, steps.filter((_x, i) => i !== index));
      this.setState({
        recipe: {
          ...recipe,
          steps: steps.filter((_x, i) => i !== index)
        }
      });
    } else {
      this.setState({
        recipe: {
          ...recipe,
          steps: [
            ...steps,
            { stepdescription: '', time: undefined }
          ]
        }
      });
    }
  }

  addOrRemovePhoto = (key) => (index, photoLink) => {
    const { recipe } = this.state;
    const { photos } = recipe;
    if (key) {
      console.log(index, photos.filter((_x, i) => i !== index));
      this.setState({
        recipe: {
          ...recipe,
          photos: photos.filter((_x, i) => i !== index)
        }
      });
    } else {
      this.setState({
        recipe: {
          ...recipe,
          photos: [
            ...photos,
            { photoLink }
          ]
        }
      });
    }
  }

  render() {
    const { recipe } = this.state;
    const { name, ingredients, steps, photos } = recipe;
    const { requestStatus } = this.state;
    return (
      <div className="create-recipe">
        <h1>Create new {name ? name : 'recipe'}</h1>
        <Paper
          style={{
            padding: '30px'
          }}
        >
          <form
            onSubmit={(e) => {
              this.setState({ requestStatus: 'uploading...' });
              const { recipe } = this.state;
              const body = JSON.stringify({
                ...recipe,
                photos: recipe.photos.filter((photo) => photo.photoLink)
              });
              fetch('/api/recipes', {
                method: 'POST',
                headers: {
                  'Authorization': 'Bearer ' + localStorage.getItem('authToken'),
                  'Content-Type': 'application/json'
                }, body })
                  .then((res) => {
                    if (res.ok) {
                      this.setState({ requestStatus: 'done!' });
                      return res;
                    }
                  }).then((res) => res.json())
                  .then((recipe) => {
                    console.log(recipe.recipeid);
                  });
              console.log(this.state.recipe);
              e.preventDefault();
            }}
          >
            <TextField
              name=''
              onChange={(e) => {
                this.setState({
                  recipe: {
                    ...recipe,
                    name: e.target.value
                  }
                });
              }}
              required
              label="Dish name"
              color={'secondary'}
              variant="outlined"
              fullWidth
            />
            <h3>Recipe details</h3>
            <TextareaAutosize
              onChange={(e) => {
                this.setState({
                  recipe: {
                    ...recipe,
                    description: e.target.value
                  }
                });
              }}
              placeholder='Recipe description'
              style={{
                width: '100%'
              }}
              rowsMin={3}
            />
            <div className="lists-wrapper">
              <div className="lists__ingredients">
                <h3>Ingredients</h3>
                {
                  ingredients.map((ing, key) =>
                    <Ingredient
                      handleChange={(value, index) => {
                        const { recipe } = this.state;
                        const { ingredients } = recipe;
                        ingredients[index].name = value;
                        this.setState({ recipe: {
                          ...recipe,
                          ingredients
                        } });
                      }}
                      placeholder="Ingredient"
                      addOrRemove={this.addOrRemoveIngredient(key)}
                      value={ing.name}
                      key={key}
                      index={key}
                    />)
                }
              </div>
              <div className="lists__steps">
                <h3>Cooking steps</h3>
                {
                  steps.map((step, key) =>
                    <Step
                      handleChange={(value, index) => {
                        const { recipe } = this.state;
                        const { steps } = recipe;
                        console.log(typeof value, value);
                        if (typeof value === 'number') {
                          steps[index].time = value;
                        } else {
                          steps[index].stepdescription = value;
                        }
                        this.setState({ recipe: {
                          ...recipe,
                          steps
                        } });
                      }}
                      placeholder="Step"
                      addOrRemove={this.addOrRemoveStep(key)}
                      value={{ desc: step.stepdescription, time: step.time }}
                      key={key}
                      index={key}
                    />)
                }
              </div>
            </div>
            <h3>Add photos</h3>
            <div className="add-photos">
              {
                photos.map((photo, index) =>
                  (!index || photo.photoLink) &&
                  <RecipePhotoThumbnail
                    photo={photos[index].photoLink}
                    key={index}
                    index={index}
                    handleClick={this.addOrRemovePhoto(index)}
                    handleChange={(value, index) => {
                      const { recipe } = this.state;
                      const { photos } = recipe;
                      photos[photos.length - 1].photoLink = value;
                      this.setState({ recipe: {
                        ...recipe,
                        photos
                      } }, () => {
                        console.log(this.state.recipe.photos);
                      });
                    }}
                  />
                )
              }
            </div>
            <p>
              <Button type='submit'>{requestStatus}</Button>
            </p>
          </form>
        </Paper>
      </div>
    );
  }
}

CreateRecipe.propTypes = {};

export default CreateRecipe;


