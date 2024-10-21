import React, { useEffect, useState } from "react";
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from "../../AxiosConfig";

const UserHome = () => {
  const [storeList, setStoreList] = useState([]);
  const [userRatings, setUserRatings] = useState({});

  useEffect(() => {
    const getAllUsers = async () => {
      try {
        const response = await AxiosConfig.get("store/getallstores");
        console.log(response);
        setStoreList(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getAllUsers();
  }, []);

 const handleRatingChange = (storeId, rating) => {
  const updatedRatings = { ...userRatings }; 
  updatedRatings[storeId] = rating; 
  setUserRatings(updatedRatings); 
};

const submitRating = async (storeId) => {
  const userId = JSON.parse(localStorage.getItem("loggedInUserId"));
  const score = userRatings[storeId]; 
  const ratingRequest = {
    userId: userId,
    score: score,
  };

  try {
    const response = await AxiosConfig.post(`store/submitrating/${storeId}`, ratingRequest);
    console.log("Rating submitted successfully:", response.data);
    alert("Rating submitted successfully!");
    window.location.reload();
  } catch (error) {
    console.error("Error submitting rating:", error);
  }
};

  return (
    <>
      <div className="page-container">
        <Navbar />
        <br></br> <br></br>
        <div className="heading">
          <h1>User Home </h1>
        </div>
        <h2 style={{ fontFamily: "TimesNewRoman", marginLeft: "40%" }}>
          STORES LIST{" "}
        </h2>
        <div>
          <table className="table table-head">
            <thead>
              <tr>
                <th> Id </th>
                <th> Name </th>
                <th> Owner </th>
                <th> Email </th>
                <th> Address </th>
                <th> Avarage Rating </th>
                <th> Your Rating </th>
                <th> Edit Rating </th>
              </tr>
            </thead>
            <tbody>
              {storeList &&
                storeList.map((data) => (
                  <tr key={data.id}>
                    <td>{data.id} </td>
                    <td>{data.storeName} </td>
                    <td>{data.storeOwner.name}</td>
                    <td>{data.email} </td>
                    <td>{data.storeAddress} </td>
                    <td>{data.averageRating} </td>
                    <td>
                      <select
                        value={userRatings[data.id] || ""} 
                        onChange={(e) =>
                          handleRatingChange(data.id, e.target.value)
                        } 
                      >
                        <option value="" disabled>
                          Select Rating
                        </option>{" "}
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                      </select>
                    </td>

                    <td>
                      <button onClick={() => submitRating(data.id)}>
                        Submit Rating
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default UserHome;
