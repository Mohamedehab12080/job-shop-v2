import React, { useEffect, useState } from 'react'
import SearchIcon from '@mui/icons-material/Search'
import { Brightness4, Close, Search } from '@mui/icons-material'
import { Button, Grid, IconButton, Slide, TextField } from '@mui/material'
import { useDispatch, useSelector } from 'react-redux'
import { fetchAllFields } from '../../store/fields/Action'
import { useFormik } from 'formik'
import { findFilteredPosts } from '../../store/Post/Action'
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
const style = {
    position: "relative",
    width: 600,
    bgcolor: "background.paper",
    border: "none",
    boxShadow: 24,
    p: 4,
    outline: "none",
    borderRadius: 4,
    maxHeight: "80vh",
    overflowY: "auto", // Enable scrolling
  };
  const slideStyle = {
    height: '100%',
    overflowY: 'auto',
    scrollbarWidth: 'none', // Hide scrollbar for Firefox
    '&::WebkitScrollbar': {
      display: 'none', // Hide scrollbar for Chrome, Safari, Edge
    },
  };
const RightSection = () => {

    const auth=useSelector(state=>state.auth);
    const fieldReducer=useSelector(state=>state.fieldReducer);
    const [filterInputFields, setFilterInputField] = useState("");
    var [filteredFields, setFilteredFields] = useState([]);
    const [fields,setFields]=useState([]);
    const [selectedField,setSelectedField]=useState("");
    const[openSearch,setOpenSearch]=useState(false);
    const handleCloseSearch=()=>setOpenSearch(false);
    const handleOpenSearch=()=>setOpenSearch(true);

    const handleRemoveField=()=>
      {
        setSelectedField("");
        formik.setFieldValue("fieldName","");
      }
    const dispatch=useDispatch();
    const dispatch2=useDispatch();

    const handleAddField = (value)=>
    {
      setSelectedField(value);
      formik.setFieldValue("fieldName",value);
    }
    const handleChangeTheme=()=>
    {
        console.log("Change Theme")
    }

    useEffect(()=>
    {
      dispatch(fetchAllFields());
    },[dispatch]);

    useEffect(()=>
      {
       setFields(fieldReducer.fields);
      },[fieldReducer.fields]);


    const handleFilterFields=(input)=>
        {
          const filtered=fields.filter((field)=>
          {
            return field.toLowerCase().includes(input.toLowerCase());
          });
            setFilteredFields(filtered);
            setFilterInputField(input);
        }
        const handleSubmit = async(values,actions)=>
          {
            console.log("values Form : ",values);
            dispatch2(findFilteredPosts(values));
          }
        const formik =useFormik({
          initialValues:{
            title:"",
            location:"",
            employmentType:"",
            createdDate:"",
            companyName:"",
            fieldName:""
          },
          onSubmit: handleSubmit,
        })
  return (
  <div style={{ borderLeft: '1px solid #ccc' }}>
        <div className='py-5 sticky top'>
            <div className='relative flex items-center'>
                <input type="text"  placeholder='Filter Accounts' className='py-3 rounded-full text-gray-500 w-full pl-12' />
                <div className='absolute top-0 left-0 pl-3 pt-3'>
                    <SearchIcon className='text-gray-500'/>
                </div>
                <Brightness4 className='ml-3 cursor-pointer' onClick={handleChangeTheme}/>
                
            </div>
            <section className='ml-5'>
                <h1 className='text-xl font-bold'></h1>
            </section>
        </div>


      {auth.user.userType === "jobSeeker" && (
      
      <>
      <Grid item xs={12} container className='mt-2' justifyContent="center">
            {openSearch ? (
              <IconButton
                onClick={handleCloseSearch}
                aria-label="show"
                size="larg"
              >
                 <Search /> <ArrowForwardIosIcon />
              </IconButton>
            ):(
              <IconButton
                  onClick={handleOpenSearch}
                  aria-label="show"
                  size="larg"
                >
                  <ArrowBackIosIcon /> <Search />
                </IconButton>
            )}
      </Grid>
      <Slide
          direction="left"
          in={openSearch}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 1200, exit: 1000 }}
          transitiontimingfunction="ease-in-out" 
          style={slideStyle}
      >
      <form onSubmit={formik.handleSubmit}>
          
          <div className='py-5 sticky ml-3'>
                <Grid item xs={12} container justifyContent={'center'}>
                <TextField
                    fullWidth
                    id="filterFields"
                    name="filterFields"
                    label="Filter Fields"
                    value={filterInputFields}
                    onChange={(e) => handleFilterFields(e.target.value)}
                />
                </Grid>
  
                
                        {selectedField !=="" &&(
                         <Grid item xs={12} container className='mt-2' justifyContent="center">
                            <span>{selectedField}</span>
                            <IconButton
                              onClick={() => handleRemoveField(selectedField)}
                              aria-label="delete"
                              size="small"
                            >
                              <Close />
                            </IconButton>
                         </Grid>
                        )}

           <Grid item xs={12} className='mt-3' container justifyContent="center">
                <div
                  className="skills-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputFields === "" ? fields : filteredFields)
                    .filter((field) => !selectedField.includes(field))
                    .map((field, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddField(field)}
                      >
                        {field}
                      </Button>
                    ))}
                </div>
              </Grid>
              <div className='py-5'>
              
                <Grid item container justifyContent="center">
                    <Button type="submit" 
                    variant="contained" 
                    color="primary">
                     <Search />  Submit
                    </Button>
                </Grid>
              </div>
          </div>

        </form>
        </Slide>
       </>
      )}
  
  </div>
  )
}

export default RightSection