package it.saimao.recyclerview2.adapters;

import it.saimao.recyclerview2.model.FriendRequest;

public interface FriendItemClickListener {
    void onConfirm(FriendRequest friendRequest);
    void onDelete(String friendName);
}
