import React from 'react';
import Message from '../messages/Message';
import { server_url } from '../config';
import s from './Posts.module.css';
import { withRouter } from 'react-router-dom';

class Posts extends React.Component {
    constructor(props) {
        super(props);
        this.postInputHandler = this.postInputHandler.bind(this);
        this.page = 0;
        this.size = 50;
        this.state = {
            messages : [],
            postText : ""
        };
    }

    componentDidMount() {
        let url = new URL(server_url + '/getPosts');
        url.searchParams.append('page', this.page);
        url.searchParams.append('size', this.size);
        fetch(url,
            {
                method: "GET",
                credentials: 'include'
            }).then(response => response.json()).then(json => {
            if (!json.error) {
                this.page++;
                this.setState({
                    messages : json
                });
            }
            else
                console.log(json.error);
        }).catch(console.log);
    }

    postInputHandler(e) {
        this.setState({
          postText: e.target.value
        });
    }

    render() {
        return (
            <div>
                <div className={s.items__container}>
                    {this.state.messages.map(m => 
                        <Message
                            key={m.messageId}
                            username={m.senderName}
                            date={m.date}
                            text={m.text}
                        />
                    )}
                </div>
            </div>
        )
    }
}

export default withRouter(Posts);