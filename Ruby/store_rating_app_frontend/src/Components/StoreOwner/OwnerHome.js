import React, { useEffect, useState } from 'react';
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from '../../AxiosConfig';

const OwnerHome = () => {
  return (
    <>
      <div className="page-container">
        <Navbar />
        <br></br> <br></br>
        <div className="heading">
          <h1>Store Owner Home </h1>
        </div>
      </div>
    </>
  );
};

export default OwnerHome;
