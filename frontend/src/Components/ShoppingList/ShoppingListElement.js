import React, { Component } from 'react';
import PropTypes from 'prop-types';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { TextField } from '@material-ui/core';
import ArrowDropUpIcon from '@material-ui/icons/ArrowDropUp';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';
import IconButton from '@material-ui/core/IconButton';
import './ShoppingListElement.sass';

class ShoppingListElement extends Component {
  constructor(props) {
    super(props);
    this.elementRef = React.createRef();
  }

  render() {
    const { element, index, handlePosition,
      listSize, addOrRemoveIngredient } = this.props;
    return (
      <div ref={this.elementRef} className='element-wrapper'>
        <IconButton onClick={addOrRemoveIngredient}>
          <AddCircleOutlineIcon
            style={{
              transform: index ? 'rotateZ(45deg)' : ''
            }}
          />
        </IconButton>
        <TextField
          fullWidth
          value={element}
          onChange={(e) => {

          }}
          size="small"
          color="secondary"
          style={{ margin: '5px 10px' }}
        />
        <div className="change-position">
          <IconButton
            onClick={() => {
              if (!index) return;
              this.elementRef.current.style.transform = 'translateY(-48px)';
              setTimeout(() => {
                this.elementRef.current.style.transform = '';
              }, 400);
              handlePosition(index, 'up');
            }}
          >
            <ArrowDropUpIcon />
          </IconButton>
          <IconButton
            onClick={() => {
              if (index + 1 >= listSize) return;
              this.elementRef.current.style.transform = 'translateY(48px)';
              setTimeout(() => {
                this.elementRef.current.style.transform = '';
              }, 400);
              handlePosition(index + 1, 'up');
            }}
          >
            <ArrowDropDownIcon />
          </IconButton>
        </div>
      </div>
    );
  }
}

ShoppingListElement.propTypes = {
  element: PropTypes.string,
  index: PropTypes.number,
  handlePosition: PropTypes.func,
  listSize: PropTypes.number,
  addOrRemoveIngredient: PropTypes.func
};

export default ShoppingListElement;
