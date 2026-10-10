def play_game(model, qmaze, pirate_cell):
    qmaze.reset(pirate_cell)
    envstate = qmaze.observe()

    while True:
        state = np.asarray(envstate, dtype=np.float32)

        if state.ndim == 1:
            state = np.expand_dims(state, axis=0)

        # Select the action with the highest Q-value
        q_values = model(state, training=False).numpy()
        action = np.argmax(q_values[0])

        # Execute the action
        envstate, reward, status = qmaze.act(action)

        if status == "win":
            print("Pirate reached the treasure!")
            return True

        if status == "lose":
            print("Pirate lost the game.")
            return False
