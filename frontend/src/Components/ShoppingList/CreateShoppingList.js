import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';
import './CreateShoppingList.sass';
import ShoppingListElement from './ShoppingListElement';
import { withRouter } from 'react-router-dom';
import Button from '@material-ui/core/Button';

class CreateShoppingList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      elements: ['prd0', 'prd1', 'prd2', 'prd3'],
      shoppingInfo: {
        recipeid: 0,
        name: '',
        ingredients: [{ name: '' }],
        ifexternal: false,
        photos: ['']
      },
      shoppingListStatus: 'save this shopping list'
    };
  }

  addOrRemoveIngredient = (key) => (index) => {
    const { shoppingInfo } = this.state;
    const { ingredients } = shoppingInfo;
    if (key) {
      console.log(index, ingredients.filter((_x, i) => i !== index));
      this.setState({
        ingredients: {
          ...ingredients,
          ingredients: ingredients.filter((_x, i) => i !== index)
        }
      });
    } else {
      this.setState({
        ingredients: {
          ...ingredients,
          ingredients: [
            ...ingredients,
            { name: '' }
          ]
        }
      });
    }
  }

  handlePosition = (shoppingInfo) => (index) => {
    let ingredients = [...shoppingInfo.ingredients];
    ingredients = [
      ...ingredients.slice(0, index - 1),
      ingredients[index],
      ingredients[index - 1],
      ...ingredients.slice(index + 1)
    ];
    this.setState({
      shoppingInfo: {
        ...shoppingInfo,
        ingredients: [...ingredients]
      } });
  }

  componentDidMount() {
    const { shoppingInfo } = this.props.match.params;
    this.setState({ shoppingInfo: JSON.parse(decodeURIComponent(shoppingInfo)) });
  }

  render() {
    const { ingredients, name, recipeid, ifexternal, photos } = this.state?.shoppingInfo;
    const { shoppingInfo, shoppingListStatus } = this.state;
    return (
      <div className='create-shopping-list'>
        <Paper
          style={{
            padding: '30px'
          }}
        >
          <header>
            <div className="title">
              <h1>Shopping list for: </h1>
              <h3><a
                className='linked-recipe-link'
                href={`/recipe/${recipeid}?external=${ifexternal ?? true}`}
              > {name}</a></h3>
            </div>
            <a
              className='linked-recipe-link'
              href={`/recipe/${recipeid}?external=${ifexternal ?? true}`}
            ><img
                src={ photos[0] || '/fallback.jpeg'}
                alt="Recipe preview"
                style={{
                  maxHeight: '180px',
                  boxShadow: '0 0 8px black'
                }}
              /></a>
          </header>
          <hr/>
          <div className="elements-wrapper">
            {
              ingredients?.map((ingredient, index) =>
                <ShoppingListElement
                  handlePosition={this.handlePosition(shoppingInfo)}
                  addOrRemoveIngredient={this.addOrRemoveIngredient(index)}
                  key={index}
                  element={ingredient.name}
                  index={index}
                  listSize={ingredients.length}
                />
                )
            }
          </div>
          <hr/>
          <Button
            onClick={() => {
              if (shoppingListStatus === 'Saved!') return;
              const shoppingData = {
                completed: false,
                ...shoppingInfo,
                ingredients:
                  shoppingInfo.ingredients.map((ing) =>
                    ({ name: ing.name, checked: false })),
              };
              let shoppingLists = JSON.parse(localStorage.getItem('shoppingLists'));
              if (shoppingLists) {
                shoppingLists.push(shoppingData);
              } else {
                shoppingLists = [shoppingData];
              }
              localStorage.setItem('shoppingLists', JSON.stringify(shoppingLists));
              this.setState({ shoppingListStatus: 'Saved!' });
            }}
          >{shoppingListStatus}</Button>
        </Paper>
      </div>
    );
  }
}

CreateShoppingList.propTypes = {};

export default withRouter(CreateShoppingList);
