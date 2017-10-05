double minVal(char currBoard[8][8], double alpha, double beta, int depth)
{
    int i;
    struct State state;
    /* Set up the current state */
    memcpy(state.board,currBoard,64*sizeof(char));
    // Deal with depth limit
    depth--;
    if(depth<=0)
    {
        state.player=me;
        return evalBoard(&state);
    }
    // You've gotta setup the board with the correct player
    state.player = ((me==1)?2:1);
    /* Find the legal moves for the current state */
    FindLegalMoves(&state);
    // for every legal move
    for(i=0;i<state.numLegalMoves;i++)
    {
        char nextBoard[8][8];
        double max;
        memcpy(nextBoard,currBoard,sizeof(nextBoard));
        PerformMove(nextBoard, state.movelist[i], MoveLength(state.movelist[i]));
        max = maxVal(nextBoard, alpha, beta, depth);
        if(max<beta) beta=max;
        if(alpha>=beta) return alpha;
    }
    return beta;
}

double maxVal(char currBoard[8][8], double alpha, double beta, int depth)
{
    int i;
    struct State state;
    /* Set up the next state's board */
    memcpy(state.board,currBoard,64*sizeof(char));
    // Deal with depth limit
    depth--;
    if(depth<=0)
    {
        state.player = me;
        return evalBoard(&state);
    }
    // You've gotta setup the board with the correct player, 
    state.player = me;
    /* Find the legal moves for the current state */
    FindLegalMoves(&state);
    // for every legal move
    for(i=0;i<state.numLegalMoves;i++)
    {
        char nextBoard[8][8];
        double min;
        memcpy(nextBoard,currBoard,sizeof(nextBoard));
        PerformMove(nextBoard, state.movelist[i], MoveLength(state.movelist[i]));
        min = minVal(nextBoard, alpha, beta, depth);
        if(min>alpha) alpha = min;
        if(alpha>=beta) return beta;
    }
    return alpha;
}