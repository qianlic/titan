import React from 'react';
import {Route, Switch} from 'react-router-dom'
import {withStyles, AppBar, Toolbar, Typography, IconButton} from '@material-ui/core';
import {MuiThemeProvider, createMuiTheme} from '@material-ui/core/styles';
import KeyboardArrowLeftIcon from '@material-ui/icons/KeyboardArrowLeft';
import HomePage from './home/index'
import UsersPage from './users/index'

const theme = createMuiTheme({
    palette: {
        primary: {
            main: '#fff',
            contrastText: '#108ee9',
        },
        secondary: {
            main: '#000',
            contrastText: '#fff',
        },
    },
    typography: {
        useNextVariants: true,
    },
})

const styles = {
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginLeft: -18,
        marginRight: 10,
    }
}

class App extends React.Component {
    render() {
        const {classes} = this.props;
        return (
            <MuiThemeProvider theme={theme} className={classes.root}>
                <AppBar position="static">
                    <Toolbar variant="dense">
                        <IconButton className={classes.menuButton} color="inherit" disableRipple="true"
                                    aria-label="Menu">
                            <KeyboardArrowLeftIcon/>
                        </IconButton>
                        <Typography variant="h6" color="secondary">
                            o(*￣︶￣*)o
                        </Typography>
                    </Toolbar>
                </AppBar>
                <main>
                    <Switch>
                        <Route path="/users" component={UsersPage}/>
                        <Route path="/" exact component={HomePage}/>
                    </Switch>
                </main>
            </MuiThemeProvider>
        );
    }
}

export default withStyles(styles)(App);
