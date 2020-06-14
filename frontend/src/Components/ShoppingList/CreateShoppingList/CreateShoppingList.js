import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';
import './CreateShoppingList.sass';
import ShoppingListElement from '../ShoppingListElement/ShoppingListElement';
import { withRouter } from 'react-router-dom';
import Button from '@material-ui/core/Button';

/* eslint-disable no-invalid-this */
/* This component is for creating new, or dealing with
existing shopping list*/
class CreateShoppingList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isNew: false,
      shoppingInfo: {
        isCompleted: false,
        recipeid: 0,
        name: '',
        ingredients: [{ name: '' }],
        ifexternal: false,
        photos: ['']
      },
      shoppingListStatus: 'save this shopping list',
      modified: false
    };
  }

  addOrRemoveIngredient = (key) => (index) => {
    const { shoppingInfo } = this.state;
    const { ingredients } = shoppingInfo;
    if (key) {
      console.log(index, ingredients.filter((_x, i) => i !== index));
      this.setState({
        shoppingInfo: {
          ...shoppingInfo,
          ingredients: ingredients.filter((_, i) => i !== index)

        }
      });
    } else {
      this.setState({
        shoppingInfo: {
          ...shoppingInfo,
          ingredients: [
            { name: '', checked: false },
            ...ingredients
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

  handleChange = (value, index) => {
    const { shoppingInfo } = this.state;
    const { ingredients } = shoppingInfo;
    ingredients[index].name = value;
    this.setState({ shoppingInfo: {
      ...shoppingInfo,
      ingredients
    } });
  }

  checkElement = (index, state) => {
    let { ingredients } = this.state.shoppingInfo;
    ingredients = ingredients.map((ing, i) => {
      if (i === index) {
        return { name: ing.name, checked: state };
      } else return ing;
    });
    if (state) {
      ingredients = [
        ...ingredients,
        ingredients[index]
      ];
      ingredients = ingredients.filter((_, i) => i !== index);
    } else {
      ingredients = [
        ingredients[index],
        ...ingredients
      ];
      ingredients = ingredients.filter((_, i) => i !== index + 1);
    }
    this.setState({ shoppingInfo: {
      ...this.state.shoppingInfo,
      ingredients
    } });
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    if (id) {
      const shoppingList = JSON.parse(localStorage.getItem('shoppingLists'))[id];
      this.setState({ shoppingInfo: shoppingList,
        shoppingListStatus: 'mark shopping list as completed' });
    } else {
      this.setState({ isNew: true });
      const { shoppingInfo } = this.props.match.params;
      this.setState({ shoppingInfo: JSON.parse(decodeURIComponent(shoppingInfo)) });
    }
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if (prevState.shoppingInfo && prevState.shoppingInfo !== this.state.shoppingInfo) {
      const shoppingData = { ...this.state.shoppingInfo };
      let shoppingLists = JSON.parse(localStorage.getItem('shoppingLists'));
      if (!shoppingLists) return;
      shoppingLists = shoppingLists.map((list) => {
        console.log(list);
        if (list.recipeid === shoppingData.recipeid) {
          return shoppingData;
        } else return list;
      });
      localStorage.setItem('shoppingLists', JSON.stringify(shoppingLists));
    }
  }

  render() {
    const { ingredients, name, recipeid,
      ifexternal, photos, completed } = this.state?.shoppingInfo;
    const { shoppingInfo, shoppingListStatus, isNew, modified } = this.state;
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
          {completed ? <h5>This shopping list is completed</h5> : null}
          <div className="elements-wrapper">
            {
              ingredients?.map((ingredient, index) =>
                <ShoppingListElement
                  handleChange={this.handleChange}
                  isNew={isNew}
                  handlePosition={this.handlePosition(shoppingInfo)}
                  addOrRemoveIngredient={this.addOrRemoveIngredient(index)}
                  key={index}
                  element={ingredient.name}
                  checkElement={this.checkElement}
                  checked={ingredient.checked}
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
              const isCompleted =
                shoppingListStatus === 'mark shopping list as completed';
              const shoppingData = {
                ...shoppingInfo,
                completed: isCompleted,
                ingredients:
                  shoppingInfo.ingredients.map((ing) =>
                    ({
                      name: ing.name,
                      checked: isCompleted
                    })),
              };
              this.setState({ shoppingInfo: shoppingData });
              let shoppingLists = JSON.parse(localStorage.getItem('shoppingLists'));
              if (shoppingLists) {
                if (isCompleted) {
                  shoppingLists = shoppingLists.map((list) => {
                    console.log(list);
                    if (list.recipeid === shoppingData.recipeid) {
                      return shoppingData;
                    } else return list;
                  });
                } else {
                  shoppingLists.push(shoppingData);
                }
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

CreateShoppingList.propTypes = {
  match: PropTypes.object
};

export default withRouter(CreateShoppingList);
