import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';

export default class RecipePhotoThumbnail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      photo: ''
    };

    this.fileInputRef = React.createRef();
  }

  componentDidMount() {
    // this.fileInputRef.current;
  }

  render() {
    const { handleClick, index, handleChange, photo } = this.props;
    return (
      <div className='add-photos__thumbnail'>
        <input
          className='photo-upload'
          type='file'
          ref={this.fileInputRef}
          onChange={(e) => {
            const file = e.target.files[0];
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = (e) => {
              const photo = e.target.result;
              console.log(photo);
              handleChange(photo);
              this.setState({ photo });
            };
          }}
        />
        <img src={photo} alt=""/>
        <IconButton
          style={{
            position: 'absolute'
          }}
          onClick={() => {
            !index && this.fileInputRef.current.click();
            handleClick(index);
          }}
        >
          <AddCircleOutlineIcon
            style={{
              transform: index ? 'rotateZ(45deg)' : '',
              color: index ? 'white' : ''
            }}
          />
        </IconButton>
      </div>
    );
  }
}

RecipePhotoThumbnail.propTypes = {};
