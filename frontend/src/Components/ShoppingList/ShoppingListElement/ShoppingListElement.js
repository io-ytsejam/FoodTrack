import React, { Component } from 'react';
import PropTypes from 'prop-types';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import { TextField } from '@material-ui/core';
import ArrowDropUpIcon from '@material-ui/icons/ArrowDropUp';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';
import IconButton from '@material-ui/core/IconButton';
import './ShoppingListElement.sass';
import Checkbox from '@material-ui/core/Checkbox';
import Tooltip from '@material-ui/core/Tooltip';

class ShoppingListElement extends Component {
  constructor(props) {
    super(props);
    this.elementRef = React.createRef();
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if (this.props.checked) {
      this.elementRef.current.classList.add('element-wrapper--checked');
    } else {
      this.elementRef.current.classList.remove('element-wrapper--checked');
    }
  }

  render() {
    const { element, index, handlePosition,
      listSize, addOrRemoveIngredient, isNew, checked, checkElement } = this.props;
    return (
      <div ref={this.elementRef} className='element-wrapper'>
        <Tooltip title={!index ? 'Add custom element' : 'Remove element'}>
          <IconButton onClick={() => {
            addOrRemoveIngredient(index);
          }}>
            <AddCircleOutlineIcon
              style={{
                transform: index ? 'rotateZ(45deg)' : ''
              }}
            />
          </IconButton>
        </Tooltip>
        {
          !isNew ?
            <Tooltip
              title={!checked ?
                'Check if you bought it' :
                'Uncheck if you doesn\'t bought it'}
            >
              <Checkbox
                checked={checked}
                onChange={
                  (e) => {
                    checkElement(index, e.target.checked);
                  }
                }
              />
            </Tooltip> :
            null
        }
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
          <Tooltip title='Move element up'>
            <IconButton
              onClick={() => {
                if (!index) return;
                this.elementRef.current.style.transform = 'translateY(-48px)';
                setTimeout(() => {
                  this.elementRef.current.style.transform = '';
                  handlePosition(index, 'up');
                }, 400);
              }}
            >
              <ArrowDropUpIcon />
            </IconButton>
          </Tooltip>
          <Tooltip title='Move element down'>
            <IconButton
              onClick={() => {
                if (index + 1 >= listSize) return;
                this.elementRef.current.style.transform = 'translateY(48px)';
                setTimeout(() => {
                  this.elementRef.current.style.transform = '';
                  handlePosition(index + 1, 'up');
                }, 400);
              }}
            >
              <ArrowDropDownIcon />
            </IconButton>
          </Tooltip>
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
  addOrRemoveIngredient: PropTypes.func,
  isNew: PropTypes.bool
};

export default ShoppingListElement;
