
//////////////////////////////////////////////////////////////////////
//                                                                  //
//  JCSP ("CSP for Java") Libraries                                 //
//  Copyright (C) 1996-2018 Peter Welch, Paul Austin and Neil Brown //
//                2001-2004 Quickstone Technologies Limited         //
//                2005-2018 Kevin Chalmers                          //
//                                                                  //
//  You may use this work under the terms of either                 //
//  1. The Apache License, Version 2.0                              //
//  2. or (at your option), the GNU Lesser General Public License,  //
//       version 2.1 or greater.                                    //
//                                                                  //
//  Full licence texts are included in the LICENCE file with        //
//  this library.                                                   //
//                                                                  //
//  Author contacts: P.H.Welch@kent.ac.uk K.Chalmers@napier.ac.uk   //
//                                                                  //
//////////////////////////////////////////////////////////////////////

package jcsp.plugNplay;

import jcsp.lang.*;

/**
 * Bitwise <I>ors</I> two <TT>Integer</TT> streams to one stream.
 *
 * <H2>Process Diagram</H2>
 * <!-- INCORRECT DIAGRAM: <p><img src="doc-files/Or1.gif"></p> -->
 * <PRE>
 *    in0  ____
 *   -->--|    | out
 *    in1 | Or |-->--
 *   -->--|____|
 * </PRE>
 * <H2>Description</H2>
 * The Or class is a process which has an infinite loop that waits
 * a Object of type Number to be sent down each of the in1 and in2 Channels.
 * The process then calculates the bitwise OR on the intValue() of the
 * two Numbers then write the result as a new Integer down the out Channel.
 * <P>
 * <H2>Channel Protocols</H2>
 * <TABLE BORDER="2">
 *   <TR>
 *     <TH COLSPAN="3">Input Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>in1,in2</TH>
 *     <TD>java.lang.Number</TD>
 *     <TD>
 *       Both Channels can accept data from any subclass of Number. It is
 *       possible to send Floats down one channel and Integers down the
 *       other. However all values will be converted to ints.
 *     </TD>
 *   </TR>
 *   <TR>
 *     <TH COLSPAN="3">Output Channels</TH>
 *   </TR>
 *   <TR>
 *     <TH>out</TH>
 *     <TD>java.lang.Integer</TD>
 *     <TD>
 *       The output will always be of type Integer.
 *     </TD>
 *   </TR>
 * </TABLE>
 * <P>
 * <H2>Example</H2>
 * The following example shows how to use the Or process in a small program.
 * The program also uses some of the other building block processes. The
 * program generates a sequence of numbers and rounds each even number up to
 * the nearest odd number and prints this on the screen.
 *
 * <PRE>
 * import jcsp.lang.*;
 * import jcsp.plugNplay.*;
 * 
 * public class OrExample {
 * 
 *   public static void main (String[] argv) {
 * 
 *     final One2OneChannel a = Channel.one2one ();
 *     final One2OneChannel b = Channel.one2one ();
 *     final One2OneChannel c = Channel.one2one ();
 *     final One2OneChannel d = Channel.one2one ();
 * 
 *     new Parallel (
 *       new CSProcess[] {
 *         new Numbers (a.out ()),
 *         new Generate (b.out (), 1),
 *         new Or (a.in (), b.in (), c.out ()),
 *         new Successor (c.in (), d.out ()),
 *         new Printer (d.in (), "--> ", "\n")
 *       }
 *     ).run ();
 * 
 *   }
 * 
 * }
 * </PRE>
 *
 * @author P.H. Welch and P.D. Austin
 */
public final class Or implements CSProcess
{
   /** The first input Channel */
   private ChannelInput in1;
   
   /** The second input Channel */
   private ChannelInput in2;
   
   /** The output Channel */
   private ChannelOutput out;
   
   /**
    * Construct a new Or process with the input Channels in1 and in2 and the
    * output Channel out. The ordering of the Channels in1 and in2 make
    * no difference to the functionality of this process.
    *
    * @param in1 the first input Channel
    * @param in2 the second input Channel
    * @param out the output Channel
    */
   public Or(ChannelInput in1, ChannelInput in2, ChannelOutput out)
   {
      this.in1 = in1;
      this.in2 = in2;
      this.out = out;
   }
   
   /**
    * The main body of this process.
    */
   public void run()
   {
      ProcessRead[] procs = {new ProcessRead(in1), new ProcessRead(in2)};
      Parallel par = new Parallel(procs);
      while (true)
      {
         par.run();
         int i1 = ((Number) procs[0].value).intValue();
         int i2 = ((Number) procs[1].value).intValue();
         out.write(new Integer(i1 | i2));
      }
   }
}
