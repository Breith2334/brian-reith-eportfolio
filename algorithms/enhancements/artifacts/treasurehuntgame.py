def play_game(model, qmaze, pirate_cell, max_steps=None):
    qmaze.reset(pirate_cell)
    envstate = qmaze.observe()
    steps = 0

    # Set a safety limit if one is not provided
    if max_steps is None:
        max_steps = qmaze.maze.size * 4

    while steps < max_steps:
        state = np.asarray(envstate, dtype=np.float32)

        if state.ndim == 1:
            state = np.expand_dims(state, axis=0)

        # Select the best action using the trained model
        q_values = model(state, training=False).numpy()
        action = np.argmax(q_values[0])

        # Execute the action
        envstate, reward, status = qmaze.act(action)
        steps += 1

        if status == "win":
            print(f"Pirate reached the treasure in {steps} steps.")
            return True

        if status == "lose":
            print(f"Pirate lost the game after {steps} steps.")
            return False

    print(f"Maximum steps reached: {max_steps}.")
    return False
