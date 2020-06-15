import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Container } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import Chip from '@material-ui/core/Chip';
import Fastfood from '@material-ui/icons/Fastfood';
import Divider from '@material-ui/core/Divider';
import { LocalDining } from '@material-ui/icons';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import AccessTime from '@material-ui/icons/AccessTime';

class Header extends Component {
  render() {
    const { recipe } = this.props;
    return (
      <>
        <Paper
          style={{
            backgroundColor: 'white',
            padding: '10px'
          }}>
          <Container className='preparation-details-wrapper'>
            <Grid container spacing={3} style={{ justifyContent: 'space-between' }}>
              <Grid item xs={12} sm={12} lg={7} >
                <div className='chips'>
                  <Chip avatar={<Fastfood />} label={'Plates: ' + recipe?.servings} />
                  <Divider orientation='vertical' color={'black'} flexItem={true} />
                  <Chip avatar={<AccessTime />}
                    label={'Timing: '+
                    recipe.readyInMinutes ||
                    recipe.steps.reduce((acc, v) => acc.time + v.time)
                    } />
                  <Divider orientation='vertical' flexItem={true}/>
                  <Chip avatar={<LocalDining />} label={'Level: medium'} />
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
      </>
    );
  }
}

Header.propTypes = {
  recipe: PropTypes.object
};

export default Header;
