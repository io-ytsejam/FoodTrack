import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './Recipe.sass';
import { Container } from '@material-ui/core';
import Fastfood from '@material-ui/icons/Fastfood';
import Paper from '@material-ui/core/Paper';
import Chip from '@material-ui/core/Chip';
import Divider from '@material-ui/core/Divider';
import Button from '@material-ui/core/Button';
import AccessTime from '@material-ui/icons/AccessTime';
import { LocalDining } from '@material-ui/icons';
import Grid from '@material-ui/core/Grid';
import ProgressBar from './ProgressBar/ProgressBar';
import { connect } from 'react-redux';
import { increaseLoading, decreaseLoading } from '../../actions/loading';
import { withRouter } from 'react-router-dom';

class Recipe extends Component {
  constructor(props) {
    super(props);
    this.state = {
      recipe: {
        analyzedInstructions: []
      },
      error: undefined,

      wheelThrottling: false,

      currentStep: 0
    };
  }

  /* eslint-disable no-invalid-this */
  componentDidMount = async () => {
    const { increaseLoading, decreaseLoading } = this.props;
    increaseLoading();
    const apiKey = process.env.REACT_APP_SPOONACULAR_API_KEY;
    const recipeID = this.props.match.params.id;
    const thirdPartyApi =
      `https://api.spoonacular.com/recipes/${recipeID}/information?apiKey=${apiKey}`;
    // TODO: Make local API XD
    const localApi = `/api/recipe/${recipeID}`;

    let response;

    try {
      response = await fetch(localApi, { method: 'GET' });
      if (!response.ok) {
        throw new Error('Server responded with: ' + response.status.toString());
      }
    } catch (error) {
      console.warn(error.message + '.', 'Try third-party API...');
      try {
        response = await fetch(thirdPartyApi, { method: 'GET' });
        if (!response.ok) {
          throw new Error('Server responded with: ' + response.status.toString());
        }
      } catch (error) {
        console.error(error.message);
        this.setState({ error: error.message });
      }
    }

    response.json()
        .then((recipe) => {
          this.setState({ recipe });
        }).catch((error) => this.setState({ error }))
        .finally(() => decreaseLoading());
  };

  /** Throws error
   * @throws {Error} */
  throwComponentError = () => {
    const { error } = this.state;
    if (error) {
      throw new Error(error);
    }
  };

  render() {
    this.throwComponentError();
    const { recipe, currentStep } = this.state;
    return (
      <Grid container spacing={2}>
        <Grid item xs={12} md={5}>
          <div className='main-section'>
            <h1>{recipe?.title}</h1>
            <Paper
              style={{
                backgroundColor: 'white',
                padding: '10px'
              }}>
              <Container
                className='preparation-details-wrapper'
                style={{
                  // height: '50px',
                  maxWidth: '750px',
                  padding: '0',
                  display: 'flex',
                  flexWrap: 'wrap'
                }}>
                <Grid container spacing={3} style={{ justifyContent: 'space-between' }}>
                  <Grid item xs={12} sm={12} lg={7} >
                    <div
                      className='chips'
                      style={{
                        display: 'flex',
                        justifyContent: 'space-between'
                      }}>
                      <Chip avatar={<Fastfood />} label={'Plates: ' + recipe.servings} />
                      <Divider orientation='vertical' color={'black'} flexItem={true} />
                      <Chip avatar={<AccessTime />}
                        label={'Timing: '+ recipe.readyInMinutes} />
                      <Divider orientation='vertical' flexItem={true}/>
                      <Chip avatar={<LocalDining />} label={'Level: ' + null} />
                    </div>
                  </Grid>
                  <Grid item xs={12} sm={12} lg={4}>
                    <Button
                      variant='outlined'
                      style={{ float: 'right', width: '100%', minWidth: '130px' }}
                    >more details</Button>
                  </Grid>
                </Grid>
              </Container>
            </Paper>
            <img
              src={recipe?.image}
              alt={recipe?.title}
              style={{
                margin: '20px 0',
                width: '100%',
                boxShadow: '1px 1px 8px black',
              }}
            />
          </div>
        </Grid>
        <Grid item xs={12} md={7}
          style={{
            // overflowY: 'scroll',
            maxHeight: 'calc(100vh - 186px)',
            // scrollSnapType: 'y mandatory'
          }}
        >
          <h1 id='steps'>
            <a href="#steps">Steps</a>
          </h1>
          <Divider style={{ width: '-webkit-fill-available' }} />
          <div className="secondary-section"
            onWheel={(e) => {
              // This is so much hack
              e.preventDefault();
              document.querySelector('#steps').scrollIntoView();
              if (!this.state.wheelThrottling) {
                const activeStep = document.querySelector('.recipe--active');
                const { currentStep } = this.state;
                const { deltaY } = e;
                if (deltaY > 0 && activeStep.nextElementSibling) {
                  console.log(e.deltaY);
                  activeStep.classList.remove('recipe--active');
                  activeStep.nextElementSibling.classList.add('recipe--active');
                  this.setState({ currentStep: currentStep + 1 });
                } else if (deltaY < 0 && activeStep.previousElementSibling) {
                  console.log(e.deltaY);
                  activeStep.classList.remove('recipe--active');
                  activeStep.previousElementSibling.classList.add('recipe--active');
                  this.setState({ currentStep: currentStep - 1 });
                }
                this.setState((state) =>
                  ({ wheelThrottling: !state.wheelThrottling }),
                () => {
                  setTimeout(() => {
                    this.setState((state) =>
                      ({ wheelThrottling: !state.wheelThrottling }));
                  }, 50);
                });
              }
            }}
          >
            {
              recipe.analyzedInstructions[0]?.steps?.map((step, key) => (
                <Paper
                  elevation={3}
                  key={key}
                  className={!key ? 'recipe recipe--active' : 'recipe'}
                  style={{ padding: '20px', marginTop: '-5px', marginBottom: '-5px' }}>
                  <p>{step.step}</p>
                  <ProgressBar
                    currentStep={currentStep}
                    index={key}
                  />
                </Paper>
              ))
            }
          </div>
        </Grid>
      </Grid>
    );
  }
}

Recipe.propTypes = {
  match: PropTypes.object
};

export default connect(null, { increaseLoading, decreaseLoading })(withRouter(Recipe));
