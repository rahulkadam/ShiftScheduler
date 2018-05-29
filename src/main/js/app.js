const React = require('react');
const ReactDOM = require('react-dom');
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {shifts: []};
    }

    componentDidMount() {
        var myRequest = new Request('/shift/schedule?employee=10&dayspan=15&shift=2');
        console.log("myrequest :" + myRequest);
        return fetch(myRequest)
            .then((resp) => resp.json())
            .then(response => {
                return this.setState({shifts: response});
            });
    }

    render() {
        return (
            <ShiftList shifts={this.state.shifts}/>
    )
    }
}

class ShiftList extends React.Component{
    render() {
        var employees = this.props.shifts.map(shift =>
            <Shift key={shift.id} shift={shift}/>
    );
        return (
            <table>
            <tbody>
            <tr>
         <th>Id</th>
        <th>Time</th>
        <th>shiftType</th>
        <th>Employee</th>
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
        <td>{this.props.shift.id}</td>
        <td>{time.toDateString()}</td>
        <td>{this.props.shift.shiftType}</td>
        <td>{this.props.shift.employee.id} - {this.props.shift.employee.name} </td>
      </tr>
    )
  }
}

ReactDOM.render(
<App />, document.getElementById('react')
)