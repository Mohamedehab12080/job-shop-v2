import React from 'react'
import SearchIcon from '@mui/icons-material/Search'
import { Brightness4 } from '@mui/icons-material'
const RightSection = () => {
    const handleChangeTheme=()=>
    {
        console.log("Change Theme")
    }
  return (
        <div className='py-5 sticky top'>
            <div className='relative flex items-center'>
                <input type="text"  placeholder='Filter Posts' className='py-3 rounded-full text-gray-500 w-full pl-12' />
                <div className='absolute top-0 left-0 pl-3 pt-3'>
                    <SearchIcon className='text-gray-500'/>
                </div>
                <Brightness4 className='ml-3 cursor-pointer' onClick={handleChangeTheme}/>
                
            </div>
            <section className='ml-5'>
                <h1 className='text-xl font-bold'></h1>
            </section>
        </div>
  )
}

export default RightSection