import HomeIcon from "@mui/icons-material/Home"
import ApiIcon from '@mui/icons-material/Api';
import NotificationIcon from "@mui/icons-material/Notifications"
import ListAltIcon from '@mui/icons-material/ListAlt';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import PendingIcon from '@mui/icons-material/Pending';
export const navigation=[
    {
        title:"Home",
        icon:<HomeIcon/>,
        path:"/home"
    },
    {
        title:"Recommend me",
        icon:<ApiIcon/>,
        path:"/explore"
    },
    {
        title:"Notifications",
        icon:<NotificationIcon/>,
        path:"/notification"
    },
    {
        title:"Lists",
        icon:<ListAltIcon/>,
        path:"/list"
    },
    {
        title:"Profile",
        icon:<AccountCircleIcon/>,
        path:"/profile"
    },
    {
        title:"More",
        icon:<PendingIcon/>,
        path:"/more"
    },
]