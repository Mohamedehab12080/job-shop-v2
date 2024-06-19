import React, { useEffect, useState } from "react";
import { KeyboardBackspace } from "@mui/icons-material";
import { useNavigate, useParams } from "react-router-dom";
import { Divider } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { findPostById } from "../../store/Post/Action";
import PostCardCompany from "../HomeSection/posts/PostCardCompany";
import PostCardJobSeeker from "../HomeSection/posts/PostCardJobSeeker";

const PostDetails = () => {
  const navigate = useNavigate();
  const handleBack = () => navigate(-1);
  const auth = useSelector((state) => state.auth);
  const post = useSelector((state) => state.post);
  const dispatch = useDispatch();
  const [fetchedPost, setFetchedPost] = useState(null);
  const { id } = useParams();
  useEffect(() => {
    dispatch(findPostById(id));
  }, [dispatch, id]);

  useEffect(() => {
    setFetchedPost(post.post);
  }, [post.post]);

  return (
    <React.Fragment>
      <section
        className={`bg-white z-50 flex items-center sticky top-0 bg-opacity-95`}
      >
        <KeyboardBackspace className="cursor-pointer" onClick={handleBack} />
        <h1 className="py-5 text-xl font-bold opacity-90 ml-5">Post</h1>
      </section>

      <section>
        {auth.user.userType === "jobSeeker" && fetchedPost ? (
          <>
            <PostCardJobSeeker
              id={fetchedPost.id}
              employerId={fetchedPost.employerId}
              employerUserName={fetchedPost.employerUserName}
              Title={fetchedPost.title}
              description={fetchedPost.description}
              jobRequirements={fetchedPost.jobRequirments}
              location={fetchedPost.location}
              employmentType={fetchedPost.employmentType}
              companyName={fetchedPost.companyName}
              profileId={fetchedPost.profileId}
              skills={fetchedPost.skills}
              qualifications={fetchedPost.qualifications}
              field={fetchedPost.field}
              employerpicture={fetchedPost.employerpicture}
              fieldName={fetchedPost.fieldName}
              createdDate={fetchedPost.createdDate}
              remainedSkills={fetchedPost.remainedSkills}
              remainedQualifications={fetchedPost.remainedQualifications}
              state={fetchedPost.state}
              matchedQulifications={fetchedPost.matchedQulifications}
              matchedSkills={fetchedPost.matchedSkills}
              applicationCount={fetchedPost.applicationCount}
              jobSeekerId={auth.user.id}
              Experience={fetchedPost.experience}
              postImage={fetchedPost.postImage}
              statusCode={fetchedPost.statuseCode}
            />
          </>
        ) : (
          <>
            {fetchedPost !== null && (
              <PostCardCompany
                id={fetchedPost.id}
                employerId={fetchedPost.employerId}
                employerUserName={fetchedPost.employerUserName}
                Title={fetchedPost.title}
                description={fetchedPost.description}
                jobRequirements={fetchedPost.jobRequirments}
                location={fetchedPost.location}
                employmentType={fetchedPost.employmentType}
                companyName={fetchedPost.companyName}
                profileId={fetchedPost.profileId}
                skills={fetchedPost.skills}
                qualifications={fetchedPost.qualifications}
                field={fetchedPost.field}
                employerpicture={fetchedPost.employerpicture}
                fieldName={fetchedPost.fieldName}
                createdDate={fetchedPost.createdDate}
                remainedSkills={fetchedPost.remainedSkills}
                remainedQualifications={fetchedPost.remainedQualifications}
                state={fetchedPost.state}
                statusCode={fetchedPost.statuseCode}
                matchedQulifications={fetchedPost.matchedQulifications}
                matchedSkills={fetchedPost.matchedSkills}
                applicationCount={fetchedPost.applicationCount}
                Experience={fetchedPost.experience}
                postImage={fetchedPost.postImage}
              />
            )}
          </>
        )}
        <Divider sx={{ margin: "2rem 0rem" }} />
      </section>

      <section></section>
    </React.Fragment>
  );
};

export default PostDetails;
