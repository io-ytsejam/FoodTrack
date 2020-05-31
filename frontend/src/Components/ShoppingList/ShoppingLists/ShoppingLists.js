import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';
import { DimmedExpandableCard } from '../../UI/HorizontalList/HorizontalList';
import './ShoppingLists.sass';
import { withRouter } from 'react-router-dom';

class ShoppingLists extends Component {
  constructor(props) {
    super(props);
    this.state = {
      shoppingLists: []
    };
  }

  componentDidMount() {
    const shoppingLists = JSON.parse(localStorage.getItem('shoppingLists'));
    this.setState({ shoppingLists });
  }

  render() {
    const { username, history } = this.props;
    const { shoppingLists } = this.state;
    return (
      <div className='shopping-lists'>
        <Paper
          style={{
            padding: '30px',
            maxWidth: '960px'
          }}
        >
          <h1>
            {username &&
            username[0].toUpperCase() +
            username.substr(1) +
            '\'s shopping lists'}
          </h1>
          <div className="shopping-lists-wrapper">
            {
              shoppingLists?.map((list, index) =>
                <DimmedExpandableCard
                  image={list.photos[0]}
                  title={'Shopping list for ' + list.name}
                  onClick={() => {
                    history.push(`/shopping-list/${index}`);
                  }}
                />
              )
            }
          </div>
        </Paper>
      </div>
    );
  }
}

ShoppingLists.propTypes = {
  username: PropTypes.string,
  history: PropTypes.object
};

const mapStateToProps = (state) => ({
  username: state.userSession.username
});

export default connect(mapStateToProps)(withRouter(ShoppingLists));
