import React, {Component} from 'react';
import AppBar from '@material-ui/core/AppBar'
import {Toolbar} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from '@material-ui/icons/Menu';
import Typography from "@material-ui/core/Typography";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    }
}));

const TopBar = props => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <AppBar style={{
        backgroundColor: 'grey'
      }} position="static">
        <Toolbar>
          <IconButton>
            <MenuIcon />
          </IconButton>
          <Typography>
            Food Track
          </Typography>
        </Toolbar>
      </AppBar>
    </div>
  )
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      weatherForecast: []
    };
  }


  componentDidMount() {
    fetch("/api/weatherforecast")
        .then(res => {
            console.log(res);
            return res.json()
        })
        .then(weatherForecast => this.setState({ weatherForecast }))
        .catch(err => this.setState({ weatherForecast: err }))
  }

  render() {
      const { weatherForecast } = this.state;
        return (
            <div>
              <TopBar/>
              {
                weatherForecast instanceof Error ?
                    <h1>Error while fetching: <span>{ weatherForecast.message }</span></h1> :
                    <ul>
                      {weatherForecast.map(forecast => (
                          <li>
                            <h3>{forecast.date}</h3>
                            <p>Temp C: <span>{forecast.temperatureC}</span></p>
                            <p>Summary: <span>{forecast.summary}</span></p>
                          </li>
                      ))}
                    </ul>
              }
            </div>
        );
    }
}

export default App;