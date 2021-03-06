/**
 * 
 */
package com.ai.academicprojects.cannibalsandmissionaries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.ai.datastructure.puzzles.proj1.State1;

/**
 * @author Dinesh Appavoo
 *
 */
public class NodeExpansionAndBreadthFirstSearch {

	/**
	 * @param args
	 */
	public static Set<State> visitedNodes=new HashSet<State>();
	public static void main(String[] args) {

		NodeExpansionAndBreadthFirstSearch bfs=new NodeExpansionAndBreadthFirstSearch();
		State goalState=bfs.expandNodesAndDoBFS(new State(3, 3, 0, 0, true, null));

		if(goalState==null)
		{
			System.out.println("Goal unreachable");
		}else
		{
			System.out.println("LEFT SIDE OF RIVER       RIGHT SIDE OF RIVER");

			bfs.printState(goalState);
		}
	}

	public static State expandNodesAndDoBFS(State startState)
	{
		if(startState.isFinalStateReached())
		{
			return startState;
		}
		ArrayList<State> nextLevelStates;
		Queue<State> queue=new LinkedList<State>();
		startState.visited=true;
		visitedNodes.add(startState);

		queue.add(startState);
		while(!queue.isEmpty())
		{
			State s=queue.poll();

			nextLevelStates=StateGenerator.generateNextLevelStates(s);

			for(State state : nextLevelStates)
			{
				if(state.visited==false)
				{
					if(!visitedNodes.contains(state))
					{
						if (state.isFinalStateReached()) {
							return state;
						} else
						{
							queue.add(state);
						}
					}
				}
			}
		}
		return null;
	}

	public void printState(State goal)
	{
		if(goal==null)
			return;
		printState(goal.parent);
		System.out.println("    ("+goal.noOfCannibalsLeft+", "+goal.noOfMissionariesLeft+" )                   ("+goal.noOfCannibalsRight+", "+goal.noOfMissionariesRight+" )");
	}
	
/*	private static void printStates(State goalState) {

		if (goalState == null) {
			return;
		}
		printStates(goalState.parent);
		System.out.println("<State>"+goalState.toString()+"</State>");
	}

	public static void main(String args[]) {
		State initialState = new State(3, 3, 0, 0, true, null);
		State goalState = expandNodesAndDoBFS(initialState);
		if (goalState == null) {
			System.out.println("Goal state cannot be reached");
		} else
			printStates(goalState);
	}*/

}
