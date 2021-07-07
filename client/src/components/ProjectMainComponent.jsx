import React,{Component} from 'react'
import {BrowserRouter as Router,Route,Switch} from "react-router-dom"
import Header from "./HeaderComponent"
import Footer from "./FooterComponent"
import ProjectComponent from './ProjectComponent'
import Project from './Project'
class ProjectMainComponent extends Component{
    render(){
        return (
            <div>
            <Router>
               
               <>
               <Header/>
               <Switch>
                <Route path="/" exact component={ProjectComponent} />
                 <Route path="/project/:id" exact component={Project} /> 
                <Route path="/projects" exact component={ProjectComponent} />
                <Route component = {ErrorComponent}/>
                </Switch>
                <Footer/>
               </>
               
            </Router>  
            </div>    
              
        )
    }
}

export default ProjectMainComponent

function ErrorComponent(){
    return <div>Something wrong</div>
}