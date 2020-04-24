import React from 'react';
import { Card } from '@material-ui/core';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import './HorizontalList.sass';
import PropTypes from 'prop-types';
import CardHeader from '@material-ui/core/CardHeader';
import CardActions from '@material-ui/core/CardActions';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import Chip from '@material-ui/core/Chip';
import IconButton from '@material-ui/core/IconButton';
import { ArrowDownward } from '@material-ui/icons';
import { Link } from 'react-router-dom';

const HorizontalList = (props) => {
  return (
    <div className="horizontal-list">
      <h1>{props.title}</h1>
      <div className='list'>
        {props.children}
      </div>
    </div>
  );
};

HorizontalList.propTypes = {
  children: PropTypes.array,
  title: PropTypes.string
};

export default HorizontalList;

/* After card become expanded, show personal info, like how much it fits your
* taste based on history, last time seen, last time cooked, source: (API|DB)
* Maybe ingredients chips
* So yeah */
export const DimmedExpandableCard = (props) => {
  const headerRef = React.createRef();
  const mainCardRef = React.createRef();
  const { recipe } = props;
  return (
    <div
      className='dimmed-expandable-card'
      ref={mainCardRef}
      onClick={(e) => {
        const mc = document.querySelector('.material-card-override');
        if (mainCardRef.current.classList.contains('dimmed-expandable-card--expanded')) {
          console.log(e.target);
          mainCardRef.current.classList.remove('dimmed-expandable-card--expanded');
          document.querySelector('header').style.position = 'fixed';
          mc.classList.remove('material-card-override--with-transition');
          let sibling = mainCardRef.current.nextSibling;
          for (let i = 1; i < 6; i++) {
            if (!sibling) return;
            sibling.style.position = '';
            sibling.style.left = '';
            sibling = sibling.nextSibling;
          }
        } else {
          mainCardRef.current.classList.add('dimmed-expandable-card--expanded');
          let sibling = mainCardRef.current.nextSibling;
          for (let i = 1; i < 6; i++) {
            if (!sibling) return;
            sibling.style.position = 'relative';
            sibling.style.left = (310).toString() + 'px';
            sibling = sibling.nextSibling;
          }
          mc.style.transform = 'translateY(1250px)';
          setTimeout(() => {
            mc.classList.add('material-card-override--with-transition');
            mc.style.transform = 'translateY(0)';
          });
          document.querySelector('header').style.position = 'absolute';
        }
      }}
    >
      <div className="card-header">
        <p>{props.title}</p>
      </div>
      <Card
        key={props.index}
        className='material-card-override'
      >
        <CardHeader
          title={props.title}
          ref={headerRef}
          className='material-header'
        />
        <CardMedia
          title={props.title}
          inputMode={'url'}
          component="img"
          image={props.image}
          style={{
            boxShadow: '0 0 4px black'
          }}
        />
        <Divider />
        <CardActions
          className='recipe-actions'
        >
          <Link to={`/recipe/${recipe.id}`}>
            <Button
              variant="contained"
              color="secondary"
            >
              Recipe
            </Button>
          </Link>
          <Button color='secondary'>
            Shopping list
          </Button>
          <IconButton style={{ marginLeft: 'auto' }}>
            <ArrowDownward />
          </IconButton>
        </CardActions>
        <Divider />
        <CardActions
          className="ingredients-list"
        >
          {
            props.ingredients.map((ingredient, index) => (
              <Chip
                className="ingredient"
                key={index}
                variant="outlined" label={ingredient.name}
              />
            ))
          }
        </CardActions>
        <CardContent>
          <p>
            {
              props.supportingText
                  .replace(/<(.*?)>/g, '')
            }
          </p>
        </CardContent>
      </Card>
    </div>
  );
};

DimmedExpandableCard.propTypes = {
  title: PropTypes.string,
  image: PropTypes.string,
  supportingText: PropTypes.string,
  ingredients: PropTypes.array,
  recipe: PropTypes.object,
  index: PropTypes.number
};
