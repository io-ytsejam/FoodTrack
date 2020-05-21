import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';

class Summary extends Component {
  constructor(props) {
    super(props);
    this.summaryRef = React.createRef();
  }
  componentDidUpdate = () => {
    const { summary } = this.props.recipe;
    let end = 1000;
    if (summary) {
      if (summary.match('All things considered')) {
        end = summary.match('All things considered').index;
      } else if (summary.match('With a spoonacular')) {
        end = summary.match('With a spoonacular').index;
      }
      this.summaryRef.current.innerHTML = summary.substring(0, end);
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
