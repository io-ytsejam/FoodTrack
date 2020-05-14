import React from 'react';
import CardActions from '@material-ui/core/CardActions';
import { Link, Redirect } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import { connect } from 'react-redux';
import { clearLoading } from '../../actions/loading';
import { PropTypes } from 'prop-types';
import { withRouter } from 'react-router-dom';

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hasError: false,
      redirect: ''
    };
  }

  static getDerivedStateFromError(error) {
    // Update state so the next render will show the fallback UI.
    return { hasError: true };
  }


  componentDidCatch(error, errorInfo) {
    // You can also log the error to an error reporting service
    // logErrorToMyService(error, errorInfo);
    this.setState({ error, errorInfo });
    const { history } = this.props;

    history.listen((location, action) => {
      if (this.state.hasError) {
        this.setState({
          hasError: false,
        });
      }
    });
    this.props.clearLoading();
  }

  render() {
    const { location } = this.props;
    const { hasError, redirect } = this.state;
    if (redirect) {
      return <Redirect to={redirect} />;
    }
    if (hasError) {
      // You can render any custom fallback UI
      const { error } = this.state;
      return <div style={{ maxWidth: '750px' }}>
        <h1>Oops!...</h1>
        <h2>{error?.message}</h2>
        <Divider />
        <CardActions
          className='recipe-actions'
        >
          <Link to='/'>
            <Button
              variant="contained"
              color="secondary"
            >
              Home page
            </Button>
            <Button
              color='secondary'
              onClick={() => {
                this.setState({
                  ...this.state,
                  redirect: location.pathname,
                  hasError: undefined
                });
              }}
            >
              Go back
            </Button>
          </Link>
          <Button color='secondary' style={{ marginLeft: 'auto' }}>
            Search for recipe
          </Button>
        </CardActions>
      </div>;
    }

    return this.props.children;
  }
}

ErrorBoundary.propTypes = {
  children: PropTypes.object,
  clearLoading: PropTypes.func.isRequired,
  history: PropTypes.object,
  location: PropTypes.object
};

export default connect(null, { clearLoading })(withRouter(ErrorBoundary));
