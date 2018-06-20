import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
const serverUrl = 'http://localhost/socket'
let stompClient;
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedPlanete: {nomPlanete: "Aucune requête en attente"},
            planeteEnCours: {nomPlaneteEnCours: ""},
            hideMyComponent:true,
            imagePlanete: {url: ""},
            filtreLumiere:true,
            testFiltreLumiere:"App-text",
            testAjoutFiltreLumiere:"App",
            filtreLumTitre:"App-title",
        }
    }

    componentDidMount() {
        let ws = new SockJS(serverUrl);
        stompClient = Stomp.over(ws);
        stompClient.connect({}, (frame)=> {
            stompClient.subscribe("/telescope", (message) => {
                if(message.body) {
                    console.log(message.body);
                    this.setState({
                        selectedPlanete: {nomPlanete: message.body},
                        hideMyComponent:false
                    })
                }
            });
        });
    }

    render() {
        return (
            <div>
                {
                    !this.state.filtreLumiere &&
                    <div className="red-filter"></div>
                }
            <div className={this.state.testAjoutFiltreLumiere}>
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className={this.state.filtreLumTitre}>Welcome to React</h1>
                </header>
                <div className="App-intro">
                    <label className={this.state.testFiltreLumiere}>Requête en attente : </label><br/>
                    <br/>
                    <label className={this.state.testFiltreLumiere}>{this.state.selectedPlanete.nomPlanete}</label><br/>
                    <br/>
                    <label className={this.state.testFiltreLumiere}>Requête en cours : </label><br/>
                    <br/>
                    <label className={this.state.testFiltreLumiere}>{this.state.planeteEnCours.nomPlaneteEnCours}</label><br/>
                    <br/>
                    <img src={this.state.selectedPlanete.url} alt=""/><br/>
                    {/*<img src={logo} className="App-logo" alt="logo"/>*/}
                    {
                        !this.state.hideMyComponent &&
                        <div>
                            <button className="App-boutton-Accepter" onClick={this.accepter}>Accepter</button>
                            <br/>
                            <button className="App-boutton-Refuser" onClick={this.refuser}>Refuser</button>
                            <br/>

                        </div>
                    }
                    <label className={this.state.testFiltreLumiere}>Filtre lumière rouge </label>
                    <br/>
                    <label class="switch" >
                        <input type="checkbox" onClick={this.filtreLumiereRouge}/>
                        <span class="slider round"></span>
                    </label>
                    <br/>
                </div>
            </div>
            </div>
        );
    }

    accepter = () => {
        fetch("http://localhost/obs/move/" + this.state.selectedPlanete.nomPlanete, {
            method: "GET"
        })
            .then((response) => {
                console.log(response)
                return response.text();
            })
            .then((selectedPlanete) => {
                if (selectedPlanete)
                {
                    this.setState({
                        planeteEnCours:{
                            nomPlaneteEnCours: selectedPlanete
                        },
                        hideMyComponent:true,
                        imagePlanete: {logo},
                    })

                }


            })
    }

    refuser = () => {
        this.setState({hideMyComponent:true})
    }

    filtreLumiereRouge = () => {
        if(this.state.filtreLumiere === true)
        {
            this.setState({filtreLumiere:false})
            this.setState({testFiltreLumiere:"App-text2"})
            this.setState({filtreLumTitre:"App-title-filtre"})
        } else {
            this.setState({filtreLumiere:true})
            this.setState({testFiltreLumiere:"App-text"})
            this.setState({filtreLumTitre:"App-title"})
        }


    }
}

export default App;
