import React from 'react';
import {Card} from "@material-ui/core";
import CardMedia from "@material-ui/core/CardMedia";
import CardContent from "@material-ui/core/CardContent";
import PropTypes from 'prop-types';

const HorizontalList = props => {
  return (
    <div className='horizontal-list'>
      {props.children}
    </div>
  );
};

HorizontalList.propTypes = {
  children: PropTypes.object
};

export default HorizontalList;

export const DimmedExpandableCard = props => {
  return(
    <div style={{
      width: "300px",
      height: "200px",
      margin: "5px"
    }}>
      <div
        className="card-header"
        style={{
          position: 'relative',
          zIndex: 1,
          width: "300px",
          height: "200px",
        }}
      >
        <h1 style={{ fontWeight: 300 }}>{props.title}</h1>
      </div>
      <Card key={props.key} style={{
        position: "relative",
        bottom: "200px",
        width: "300px",
        maxHeight: "200px"
      }}>
        <CardMedia
          title={props.title}
          inputMode={"url"}
          component="img"
          image={props.image}
        />
        <CardContent>
          <p>
            {
              props.supportingText
                .substr(0, 100)
                .replace(/<.*>/g, '') + '...'
            }
          </p>
        </CardContent>
      </Card>
    </div>
  )
};

DimmedExpandableCard.propTypes = {
  title: PropTypes.string,
  image: PropTypes.object,
  supportingText: PropTypes.string
};