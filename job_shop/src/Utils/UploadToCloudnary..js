
import {Cloudinary} from "@cloudinary/url-gen";

export const uploadToCloudnary=async(pics)=>
{
    if(pics)
    {
        const data =new FormData();
        data.append("file",pics);
        data.append("upload_preset","instagram");
        data.append("cloud_name","dem9blvzk");
        const res=await fetch("https://api.cloudinary.com/v1_1/dem9blvzk/image/upload"
    ,
        {
            method:"post",
            body:data
        }
    )
    console.log("File Data:", res);
    const fileData = await res.json();
    

    if (fileData && fileData.url) {
        return fileData.url.toString();
    } else {
        throw new Error("Invalid response from Cloudinary: URL not found.");
    }
    }else 
    {
        console.log("Error from upload function");
    }
    
}