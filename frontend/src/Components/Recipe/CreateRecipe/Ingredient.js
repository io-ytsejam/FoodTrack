import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { TextField } from '@material-ui/core';


export default class Ingredient extends Component {
  render() {
    const { index, addOrRemove, handleChange, value, placeholder } = this.props;
    return (
      <div className="list-element">
        <IconButton onClick={() => addOrRemove(index)}>
          <AddCircleOutlineIcon
            style={{
              transform: index ? 'rotateZ(45deg)' : ''
            }}
          />
        </IconButton>
        <TextField
          value={value}
          onChange={(e) => {
            handleChange(e.target.value, index);
          }}
          size="small"
          color="secondary"
          placeholder={placeholder}
          style={{ margin: '5px 10px' }}
        />
      </div>
    );
  }
}

Ingredient.propTypes = {};
