import React, {Component} from 'react';

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