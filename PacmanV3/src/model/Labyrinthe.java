package model;

import java.util.Random;

public class Labyrinthe{

	private final static int MUR =1;

	
	private int sizetab =30;
	private int [][] lab = new int [sizetab][sizetab];
	private int nblab = 5;
	private int labCourant;

	private int [][] tab1 = {	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,1,1},
								{1,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1},
								{1,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,0,0,1},
								{1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1},
								{1,1,1,0,1,1,0,1,1,0,1,1,1,0,0,0,0,1,1,1,0,1,1,0,1,1,0,1,1,1},
								{1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1},
								{2,0,0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,0,0,0},
								{0,0,0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,0,0,0},
								{1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1},
								{1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
								{1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1},
								{1,0,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,1},
								{1,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1},
								{1,1,1,1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

	private int [][] tab2 = {	{1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,0,1,0,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,0,1,0,1,1,1,1},
								{1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,0,0,1},
								{1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1},
								{1,1,1,0,0,0,0,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,0,0,0,0,1,1,1},
								{1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,0,1,1,1},
								{2,0,0,0,1,1,0,1,1,0,0,1,1,0,0,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0},
								{0,0,0,0,1,1,0,1,1,0,0,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,0,0,0,0},
								{1,1,1,0,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,0,1,1,1},
								{1,1,1,0,0,0,0,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,0,0,0,0,1,1,1},
								{1,1,1,0,1,1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,1,1,1},
								{1,0,0,0,1,1,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,0,0,0,0,0,1},
								{1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1},
								{1,1,1,1,0,1,0,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,0,1,0,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1}};
	
	private int [][] tab3 = {	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
								{1,0,0,0,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,1},
								{1,0,1,1,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,1,1,0,1},
								{1,0,1,1,1,0,1,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,1,1,1,0,1},
								{1,0,1,1,1,0,1,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,1,1,0,1,1,1,0,1},
								{1,0,0,0,0,0,1,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,0,1},
								{1,0,0,1,0,0,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,1,0,0,1},
								{0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0},
								{0,0,0,1,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,1,0,0,0},
								{1,1,1,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,1,1,1,1},
								{2,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,1,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,1,1,1,1},
								{0,0,0,1,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,1,0,0,0},
								{0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0},
								{1,0,0,1,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,1,0,0,1},
								{1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1},
								{1,0,1,1,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,1,0,1,1,0,1},
								{1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
								{1,0,0,1,0,1,0,1,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0,1,0,1,0,0,1},
								{1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
								{1,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,1},
								{1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,0,0,1,1},
								{1,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
								{1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	
	private int [][] tab4 = {	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
								{1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
								{1,0,0,0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,0,0,1},
								{1,0,1,1,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,1,1,0,1},
								{1,0,1,1,1,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,0,0,1,1,1,0,1},
								{1,0,1,1,1,1,0,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1},
								{1,0,0,1,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,0,1},
								{0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0},
								{0,0,0,1,0,0,0,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,0,0},
								{1,1,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1},
								{2,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0},
								{1,1,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1},
								{0,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,0,0},
								{0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0},
								{1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,0,1},
								{1,0,0,0,0,1,0,0,0,1,0,1,1,1,1,1,1,1,1,0,1,0,0,0,1,0,0,0,0,1},
								{1,0,1,1,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,1,0,1,1,0,1},
								{1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
								{1,0,0,1,0,1,0,1,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0,1,0,1,0,0,1},
								{1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
								{1,0,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,0,1},
								{1,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1},
								{1,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
								{1,0,1,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	
	private int [][] tab5 = {	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,0,1,1},
								{1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1},
								{1,0,0,0,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,1},
								{1,0,1,1,0,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,0,1,1,0,1},
								{1,0,0,1,1,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,1,1,0,0,1},
								{1,1,0,0,1,1,0,1,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,0,1,1,0,0,1,1},
								{1,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
								{1,0,0,1,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,0,1},
								{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0},
								{0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1,0,1,0,1,0,0,0,1,0,0,0},
								{1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1},
								{2,0,0,0,0,1,0,0,0,1,0,1,1,0,1,1,0,1,1,0,1,0,0,0,1,0,0,0,0,0},
								{1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1},
								{0,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0},
								{0,0,0,1,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,1,0,0,0},
								{1,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,1},
								{1,0,0,0,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,1},
								{1,0,1,1,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,0,1,0,1,1,1,0,1,1,0,1},
								{1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
								{1,1,0,1,0,1,0,1,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0,1,0,1,0,1,1},
								{1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1},
								{1,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,0,1},
								{1,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1},
								{1,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
								{1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1},
								{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

	private int [][][] tab = {tab1, tab2, tab3, tab4, tab5};

	
	public Labyrinthe() {
		this.labCourant = nblab;
		labChange();
	}
	

	public int getLabCourant()
	{
		return this.labCourant;
	}
	public int[][] selectRandomLab(int n)
	{
		return this.tab[n-1];
	}
	
	public int getSize() {
		return this.sizetab;
	}
	
	public void labChange()
	{
		int n = this.getLabCourant();
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(nblab) + 1;
		while(nombreAleatoire == n)
		{
			nombreAleatoire = rand.nextInt(nblab) + 1;
		}
		this.labCourant = nombreAleatoire;
		int tab[][] = selectRandomLab(nombreAleatoire);
		for(int i = 0; i<lab.length; i++) {
			for(int j = 0; j<lab[i].length; j++) {
				this.lab[i][j] = tab[j][i];
			}
		}
	}
	
	public boolean isMur(Coordinate c) {
		return lab[c.getX()][c.getY()] == MUR;
	}


}
