import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Card} from "@material-ui/core";
import CardHeader from "@material-ui/core/CardHeader";
import Typography from "@material-ui/core/Typography";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      randomRecipes: []
    }
  }
  componentDidMount() {
    const { setIsReady } = this.props;
    setIsReady(false);
    const apiKey = 'cf77fdc39b774655be5e62c3ceb0c2b6';
    const url = `https://api.spoonacular.com/recipes/random?apiKey=${apiKey}&number=10&`;
    fetch(url, { method: 'GET' })
      .then(res => res.json())
      .then(randomRecipes => {
        this.setState({ randomRecipes: randomRecipes.recipes })
        console.log(randomRecipes.recipes)
      })
      .then(() => setIsReady(true))
      .catch(err => err.message)
  }

  render() {
    const { randomRecipes } = this.state;
    return (
      <>
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))',
          gridGap: '5px'
        }}>
          {
            randomRecipes && randomRecipes.map((recipe, key) => (
              <Card key={key} style={{
                margin: "5px"
              }}>
                <CardHeader title={recipe.title} />
                <CardContent>
                  <p>
                    {
                      recipe.summary
                        .substr(0, 100)
                        .replace(/<.*>/g, '') + '...'
                    }
                  </p>
                  <CardMedia
                    title={recipe.title}
                    inputMode={"url"}
                    component="img"
                    image={recipe.image}
                  />
                </CardContent>
              </Card>
            ))
          }
        </div>
      </>
    );
  }
}

Dashboard.propTypes = {};

export default Dashboard;