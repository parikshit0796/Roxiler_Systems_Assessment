import React, { useEffect, useState } from 'react';
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from '../../AxiosConfig';

const StoresList = () => {

  const [storeList, setStoreList] = useState([]);

  useEffect(() => {
    const getAllStores = async () => {
      try {
        const response = await AxiosConfig.get("store/getallstores");
        console.log(response)
        setStoreList(response.data);
      } catch (error) {
        console.log(error)
      }
    }
    getAllStores();
  }, []);
  return (
    <div className="page-container">
        <Navbar />
        <br></br> <br></br>
        <div className="heading">
          <h1>Stores List </h1>
        </div>

        <div>
          <table className="table table-head">
            <thead>
              <tr>
                <th> Id </th>
                <th> Name </th>
                <th> Owner </th>
                <th> Email </th>
                <th> Address </th>
                <th> Rating </th>
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
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
  )
}

export default StoresList