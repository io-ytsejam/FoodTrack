import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
  userSession: state.userSession
});

class Profile extends Component {
  render() {
    const { userSession } = this.props;
    return (
      <div>
        <h1>Hello, {userSession.name}</h1>
      </div>
    );
  }
}

Profile.propTypes = {
  userSession: PropTypes.object
};

export default connect(mapStateToProps)(Profile);
