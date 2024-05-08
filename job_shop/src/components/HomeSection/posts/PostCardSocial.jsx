import React, { useState ,useRef} from "react";
import RepeatIcon from '@mui/icons-material/Repeat'
import { Avatar } from '@mui/material'
import defaultImg from '../../common/images/default.jpg'
import myImg from '../../common/images/myPic.jpg'
import { useNavigate } from 'react-router-dom'
import Button from '@mui/material/Button'
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Groups2Icon from '@mui/icons-material/Groups2';
import axios from "axios";
import { CheckCircle } from '@mui/icons-material';
import { ErrorOutline } from '@mui/icons-material';
import { LocationOn } from '@mui/icons-material';
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import GroupIcon from '@mui/icons-material/Group';
import ApplyModal from '../../modals/JobSeekerModal/ApplicationsModals/ApplyModal'
import AddSkillsModal from '../../modals/JobSeekerModal/skillsModals/AddSkillsModal'

const PostCardSocial = ({
    id,
    employerId,
    employerUserName,
    Title,
    description,
    jobRequirements,
    location,
    employmentType,
    companyName,
    profileId,
    skills,
    qualifications,
    field,
    employerpicture,
    fieldName,
    createdDate,
    remainedSkills,
    remainedQualifications,
    state,
    matchedSkills,
    matchedQulifications,
    applicationCount
}) => {
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const [openApplyModal, setOpenApplyModal] = useState(false);
    const handleOpenApplyModal = () => setOpenApplyModal(true);
    const handleCloseApplyModal = () => setOpenApplyModal(false);
    const [openAddSkillsModal, setOpenAddSkillsModal] = useState(false);
    const handleOpenAddSkillsModal = () => setOpenAddSkillsModal(true);
    const handleCloseAddSkillsModal = () => setOpenAddSkillsModal(false);
    const jobSeekerId=3;
    const navigate = useNavigate();
    
     // You might want to make this dynamic too, depending on your application logic
    const handleClose = () => {
        setAnchorEl(null);
    };
    const uint8Array = new Uint8Array(employerpicture);

    // Convert the Uint8Array to a Blob
    const blob = new Blob([uint8Array]);
    const postRef = useRef(null);
    // Create a URL for the Blob
    const imageUrl = URL.createObjectURL(blob);
    return (
        <div ref={postRef} className=''>
            <div className='flex space-x-5'>
            <Avatar
                onClick={() => navigate(`/profile/${profileId}`)}
                className='cursor-pointer'
                alt="userName"
                src={imageUrl}
                />


                <div className='w-full'>
                    <div className='flex justify-between items-center'>
                        <div className='flex cursor-pointer items-center space-x-2'>
                            <span className='font-semibold'>{employerUserName}</span>
                            <span className='text-gray-600'>{`@${companyName.split(" ").join("_").toLowerCase()}`}</span>
                        </div>
                        <div>
                            <Button
                                variant="contained"
                                id="basic-button"
                                onClick={handleOpenApplyModal}
                                disabled={!state}
                            >
                                Apply
                            </Button>
                        </div>

                    </div>
                   <div className='flex justify-between items-center'>
                     {applicationCount !==0 && (
                          <div> <GroupIcon />  Application Count : {applicationCount}</div>
                        )}
                          {!state ? (
                            <div>You are not qualified</div>
                          ):(
                            <div></div>
                          )}
                       </div>
<div className="mt-2">
  <div className="cursor-pointer">
    <p className="mb-2 p-0"> <strong>Title: </strong> {Title}</p>
    <p className="mb-2 p-0 text-gray-600"> <strong>Description: </strong>  {description}</p>
    <div className="flex flex-wrap items-center">
      <p className="mb-2 p-0 text-gray-600 mr-3">
      <FmdGoodIcon className="text-[#1d9bf0]"/>
        <strong>Location:</strong> {location}
      </p>
      <p className="mb-2 p-0 text-gray-600 mr-3">
        <strong>Employment Type:</strong> {employmentType}
      </p>
      {/* Render other details here */}
    </div>
    {/* Render more details here */}
    {/* <img className="w-[28rem] border border-gray-400 p-5 rounded-md" src={myImg} alt="" /> */}
  </div>

  <div className="flex flex-wrap items-center">
    <p className="mb-2 p-0 text-gray-600"><strong>Field :</strong> {fieldName}</p>
    </div>
  <div className="flex flex-wrap items-center">
    {/* Render skills if available */}
    {matchedSkills && matchedSkills.length > 0 && (
      <div className="flex items-center text-gray-600 mr-3">
        <CheckCircle style={{ color: 'green' }} />
        <strong>Matched Skills:</strong>&nbsp;
        {matchedSkills.map((skill, index) => (
          <span key={index} className="mr-1"> {index > 0 && ','} {skill}</span>
        ))}
      </div>
    )}
   {remainedSkills && remainedSkills.length > 0 && (
  <div className="flex items-center text-gray-600 mr-3">
    <ErrorOutline style={{ color: 'red' }} />
    <strong>Remained Skills:</strong>&nbsp;
    {remainedSkills.map((skill, index) => (
      <span
        key={index}
        className="mr-1"
        onClick={() => handleOpenAddSkillsModal()}
        style={{ cursor: 'pointer', textDecoration: 'underline' }}
      >
        {index > 0 && ','} {skill}
      </span>
    ))}
  </div>
)}

   
  </div>

<hr></hr>

  <div className="flex flex-wrap items-center">
 {/* Render qualifications if available */}
 {matchedQulifications && matchedQulifications.length > 0 && (
      <div className="flex items-center text-gray-600 mr-3">
        <CheckCircle style={{ color: 'green' }} />
        <strong>Matched Qualifications:</strong>&nbsp;
        {matchedQulifications.map((qualification, index) => (
      <span key={index} className="mr-1">{index > 0 && ','} {qualification} </span>
    ))}
      </div>
    )}
     {remainedQualifications && remainedQualifications.length > 0 && (
  <div className="flex items-center text-gray-600 mr-3">
    <ErrorOutline style={{ color: 'red' }} />
    <strong>Remained Qualifications:</strong>&nbsp;
    {remainedQualifications.map((remainedQual, index) => (
      <span
        key={index}
        className="mr-1"
        onClick={() => handleOpenAddSkillsModal()}
        style={{ cursor: 'pointer', textDecoration: 'underline' }}
      >
        {index > 0 && ','} {remainedQual}
      </span>
    ))}
  </div>
)}
 
    {/* Render more additional details here */}
  </div>
  <div>  <hr style={{ 
  border: 'none',
  height: '2px', /* Increase height for better visibility */
  backgroundColor: '#012', // Change to your desired color
  margin: '20px 0', // Adjust margins as needed
  borderRadius: '2px', // Add border radius for a softer appearance
  boxShadow: '0 2px 2px rgba(0, 0, 0, 0.1)' // Add a subtle shadow for depth
}} />
</div>
</div>

                </div>
                <section>
                    <ApplyModal
                     field={fieldName} 
                     postIdd={id} 
                     openApplyModal={openApplyModal} 
                     handleClose={handleCloseApplyModal}
                     skills={matchedSkills}
                     Qualifications={matchedQulifications}
                     id={jobSeekerId}
                     postRef={postRef}// Receive the postRef
                     />
                </section>

                <section>
                    <AddSkillsModal 
                    openAddSkillsModal={openAddSkillsModal}
                     id={jobSeekerId}
                     skills={remainedSkills}
                     Qualifications={remainedQualifications}
                     handleClose={handleCloseAddSkillsModal} />
                </section>
            </div>

        </div>
    )
}

export default PostCardSocial;
