import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';

class Summary extends Component {
  constructor(props) {
    super(props);
    this.summaryRef = React.createRef();
  }
  componentDidUpdate = () => {
    const { description } = this.props.recipe;
    let end = 1000;
    if (description) {
      if (description.match('All things considered')) {
        end = description.match('All things considered').index;
      } else if (description.match('With a spoonacular')) {
        end = description.match('With a spoonacular').index;
      }
      this.summaryRef.current.innerHTML = description.substring(0, end);
    }
  }
  render() {
    return (
      <Paper
        style={{
          padding: '20px'
        }}
      >
        <div
          ref={this.summaryRef}
        />
      </Paper>
    );
  }
}

Summary.propTypes = {
  recipe: PropTypes.object
};

export default Summary;
