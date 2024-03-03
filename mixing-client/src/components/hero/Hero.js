import React from "react";
import './Hero.css';
import Carousel from 'react-material-ui-carousel';
import { Paper } from '@mui/material';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCirclePlay } from "@fortawesome/free-solid-svg-icons";
import { Link, useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";

const Hero = ({videos}) => {

    const navigate = useNavigate();

    function reviews(movieId){
        navigate(`/Reviews/${movieId}`);
    }

    return(
        <div>
            <Carousel>
                {
                    videos.map((video)=>{
                        return(
                            <Paper>
                                <div className="movie-card-container">
                                <div className="movie-card" style={{"--img": `url(${video.backdrops[0]})`}}>
                                        <div className="movie-detail">
                                            <div className="movie-poster">
                                                <img src={video.poster} alt=""/>
                                            </div>
                                            <div className="movie-title">
                                                <h4>{video.title}</h4>
                                            </div>
                                            <div className="movie-buttons-container">
                                                <Link to={`/Trailer/${video.trailerLink.substring(video.trailerLink.length -11)}`}>
                                                <div className="play-button-icon-container">
                                                    <FontAwesomeIcon className="play-button-icon"
                                                        icon= {faCirclePlay}
                                                    />
                                                </div>
                                                </Link>

                                                <div className="movie-review-button-container">
                                                    <Button variant="info" onClick={() => reviews(video.imdbId)}>Reviews</Button>
                                                </div>
                                                
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </Paper>
                        )
                    })
                }
            </Carousel>
        </div>
    )
}

export default Hero;