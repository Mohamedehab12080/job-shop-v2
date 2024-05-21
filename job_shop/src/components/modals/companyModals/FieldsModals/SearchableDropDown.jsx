
import React, { useState, useEffect } from 'react';
import { TextField, Menu, MenuItem, Paper, ClickAwayListener } from '@mui/material';

const SearchableDropdown = ({ options }) => {
  const [anchorEl, setAnchorEl] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredOptions, setFilteredOptions] = useState(options);

  useEffect(() => {
    setFilteredOptions(
      options.filter(option => 
        option.toLowerCase().includes(searchTerm.toLowerCase())
      )
    );
  }, [searchTerm, options]);

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
    if (!anchorEl) {
      setAnchorEl(event.currentTarget);
    }
  };

  const handleOptionClick = (option) => {
    setSearchTerm(option);
    setAnchorEl(null);
  };

  const handleClickAway = () => {
    setAnchorEl(null);
  };

  return (
    <div>
      <TextField
        fullWidth
        value={searchTerm}
        onChange={handleInputChange}
        variant="outlined"
        label="Select a location"
      />
      <ClickAwayListener onClickAway={handleClickAway}>
        <Paper>
          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={() => setAnchorEl(null)}
          >
            {filteredOptions.map((option, index) => (
              <MenuItem key={index} onClick={() => handleOptionClick(option)}>
                {option}
              </MenuItem>
            ))}
          </Menu>
        </Paper>
      </ClickAwayListener>
    </div>
  );
};

export default SearchableDropdown;
