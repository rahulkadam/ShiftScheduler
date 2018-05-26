const React = require('react');
const ReactDOM = require('react-dom');
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {shifts: []};
    }

    componentDidMount() {
        var myRequest = new Request('/shift/list');
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
            <th>First Name</th>
        <th>Last Name</th>
        <th>Description</th>
        </tr>
        {employees}
        </tbody>
        </table>
    )
    }
}

class Shift extends React.Component{
    render() {
     return (
      <tr>
        <td>{this.props.shift.id}</td>
        <td>{this.props.shift.type}</td>
        <td>{this.props.shift.time}</td>
      </tr>
    )
  }
}

ReactDOM.render(
<App />, document.getElementById('react')
)