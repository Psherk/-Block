package Block.Network.Messages;

import Block.Network.ConnectionHandler;

public interface IMessage {
    abstract void invoke(ConnectionHandler Connection);
}
