import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './Recipe.sass';
import Divider from '@material-ui/core/Divider';
import Grid from '@material-ui/core/Grid';
import { connect } from 'react-redux';
import { increaseLoading, decreaseLoading } from '../../actions/loading';
import { nextStep, prevStep, raiseTime, lowerTime, setTime } from '../../actions/cooking';
import { withRouter } from 'react-router-dom';
import Tomatometer from './Tomatometer/Tomatometer';
import Step from './Step/Step';
import Summary from './Summary/Summary';
import Header from './Header/Header';
import Ingredients from './Ingredients/Ingredients';

class Recipe extends Component {
  constructor(props) {
    super(props);
    this.state = {
      recipe: {
        analyzedInstructions: []
      },
      error: undefined,
      wheelThrottling: false
    };
    this.summaryRef = React.createRef();
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

  componentDidUpdate(prevProps, prevState, snapshot) {
    const { inProgress, passedTime, totalTime, raiseTime } = this.props;
    const { cookingProgressInterval } = this.state;
    if (prevProps.inProgress !== inProgress && inProgress) {
      this.setState({
        cookingProgressInterval: setInterval(() => {
          raiseTime();
        }, 1000) });
    }
    if (prevProps.inProgress !== inProgress && !inProgress) {
      clearInterval(cookingProgressInterval);
    }
    if (inProgress &&
      (passedTime.minutes * 60 + passedTime.seconds >= totalTime) && passedTime.seconds) {
      this.props.setTime(-1);
      console.log('STOP');
    }
  }

  getStepTime = (recipe, index) =>
    (recipe.readyInMinutes*60/recipe.analyzedInstructions[0].steps.length) +
    (recipe.readyInMinutes*60/recipe.analyzedInstructions[0].steps.length) * index

  scrollSteps = (e) => {
    // return;
    // This is so much hack
    // e.preventDefault();
    document.querySelector('#steps').scrollIntoView();
    if (!this.state.wheelThrottling) {
      const activeStep = document.querySelector('.recipe--active');
      if (!activeStep) return;
      const { nextStep, prevStep } = this.props;
      const { deltaY } = e;
      if (deltaY > 0 && activeStep.nextElementSibling) {
        // Scroll down
        activeStep.classList.remove('recipe--active');
        activeStep.nextElementSibling.classList.add('recipe--active');
        nextStep();
      } else if (deltaY < 0 && activeStep.previousElementSibling) {
        // Scroll up
        activeStep.classList.remove('recipe--active');
        activeStep.previousElementSibling.classList.add('recipe--active');
        prevStep();
      }
      this.setState((state) =>
        ({ wheelThrottling: !state.wheelThrottling }),
      () => {
        setTimeout(() => {
          this.setState((state) =>
            ({ wheelThrottling: !state.wheelThrottling }));
        }, 500);
      });
    }
  }

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
    const { recipe } = this.state;
    const { inProgress, passedTime } = this.props;
    return (
      <Grid container spacing={2}>
        <Grid item xs={12} md={5}>
          <div className='main-section'>
            <h3>{recipe?.title}</h3>
            <Header recipe={recipe} />
            <Ingredients recipe={recipe} />
            <img
              src={recipe?.image}
              alt={recipe?.title}
              style={{
                margin: '20px 0',
                width: '100%',
                boxShadow: '1px 1px 8px black',
              }}
            />
            <Summary recipe={recipe} />
          </div>
        </Grid>
        <Grid item xs={12} md={7}
          style={{
            // overflowY: 'scroll',
            maxHeight: 'calc(100vh - 186px)',
            // scrollSnapType: 'y mandatory'
          }}
        >
          <div
            style={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'space-between'
            }}
          >
            <h3 id='steps'>
              <a href="#steps">{
                inProgress ?
                  `Passed ${passedTime.minutes}:${passedTime.seconds}` :
                  'Ready'
              }</a>
            </h3>
            <Tomatometer
              readyInMinutes={recipe.readyInMinutes}
            />
          </div>
          <Divider style={{ width: '-webkit-fill-available' }} />
          <div className="secondary-section"
            onWheel={this.scrollSteps}
          >
            {
              recipe.analyzedInstructions[0]?.steps?.map((step, key) => (
                <Step
                  step={step}
                  index={key}
                  time={this.getStepTime(recipe, key)}
                />
              ))
            }
          </div>
        </Grid>
      </Grid>
    );
  }
}

Recipe.propTypes = {
  match: PropTypes.object,
  inProgress: PropTypes.bool,
  currentStep: PropTypes.number,
  nextStep: PropTypes.func,
  prevStep: PropTypes.func,
  passedTime: PropTypes.object
};

const mapStateToProps = (state) => ({
  inProgress: state.cooking.inProgress,
  currentStep: state.cooking.currentStep,
  totalTime: state.cooking.total,
  passedTime: state.cooking.passed
});

export default connect(
    mapStateToProps, {
      increaseLoading,
      decreaseLoading,
      nextStep,
      prevStep,
      raiseTime,
      lowerTime,
      setTime
    })(withRouter(Recipe));
