import  React , {Component} from  "react";
import ReactDOM from "react-dom";
import "./app.scss";
class App extends Component {

    constructor(props) {
        super(props);
        this.state = {shifts: [] , employee : '' , shiftCount : '' , days : '' , error : ''};
        this.handleEmployeeChange = this.handleEmployeeChange.bind(this);
        this.handleDaysChange = this.handleDaysChange.bind(this);
        this.handleShiftChange = this.handleShiftChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleEmployeeChange(event) {
        this.setState({employee: event.target.value});
    }
    handleDaysChange(event) {
        this.setState({days: event.target.value});
    }
    handleShiftChange(event) {
        this.setState({shiftCount: event.target.value});
    }

    handleSubmit(event) {
            var myRequest = new Request('/shift/schedule?employee='+this.state.employee+'&dayspan='+this.state.days+'&shift=' + this.state.shiftCount);
            console.log("myrequest :" + myRequest);
            return fetch(myRequest)
                .then((resp) => resp.json())
                .then(response => {
                    return response.error ? this.setState({shifts: [] , error : response.message}) : this.setState({shifts: response , error : ''});})
                .catch((err) => {
                    return this.setState({error : err.message});
                 });
    }

    getInputPanel() {
        return(<div style={{"marginTop" : "15px"}}>
                <label>
                Employee Count: <input type="text" name="employee" value = {this.state.employee}  onChange = {this.handleEmployeeChange}/>
                </label>
                <label>
                No Of Days : <input type="text" name="days" value = {this.state.days} onChange = {this.handleDaysChange}/>
                </label>
                <label>
                Total Shift Per Day: <input type="text" name="Shift" value = {this.state.shiftCount} onChange = {this.handleShiftChange}/>
                </label>
            <input type="submit" value="Submit" style={{"marginLeft" : "15px"}}  onClick = {this.handleSubmit}/>

        </div>);
    }

    showError() {
        return (<div>{this.state.error}</div>);
    }
    render() {
        return (<div style={{"marginLeft" : "15px"}}>
            {this.getInputPanel()}
            {this.state.shifts.length > 0 &&  <ShiftList shifts={this.state.shifts}/>}
            {this.state.error.length > 0 && this.showError()}

        </div>
    )
    }
}

class ShiftList extends React.Component{
    render() {
        var employees = this.props.shifts.map(shift =>
            <Shift key={shift.id} shift={shift}/>
    );
        return (
            <table style = {{"fontFamily": "arial, sans-serif", "borderCollapse": "collapse;width: 100%"}}>
            <tbody>
         <tr>
         <th style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>Id</th>
        <th style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>Time</th>
        <th style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>shiftType</th>
        <th style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>Employee</th>
        </tr>
        {employees}
        </tbody>
        </table>
    )
    }
}

class Shift extends React.Component{
    render() {
     let time = new Date(this.props.shift.time.millis);

     return (
      <tr>
        <td style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>{this.props.shift.id}</td>
        <td style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>{time.toDateString()}</td>
        <td style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>{this.props.shift.shiftType}</td>
        <td style= {{ border: "1px solid #dddddd", "textAlign": "left", "padding": "8px"}}>{this.props.shift.employee.id} - {this.props.shift.employee.name} </td>
      </tr>
    )
  }
}

ReactDOM.render(
<App />, document.getElementById('react')
)