/*
 * CSC252 – Sim 3
 * File: Sim3_MUX_8by1.java
 * Author: Rithvik
 * Purpose: 8-to-1 multiplexer using 3 control bits and 8 input RussWires.
 */

public class Sim3_MUX_8by1 {
    // Control bits selecting which input is routed to output
    public RussWire[] control;
    // Data inputs
    public RussWire[] in;
    // Output of the MUX
    public RussWire out;

    /** Constructor initializes wires */
    public Sim3_MUX_8by1() {
        control = new RussWire[3];
        in = new RussWire[8];
        for (int i = 0; i < 3; i++) {
            control[i] = new RussWire();
        }
        for (int i = 0; i < 8; i++) {
            in[i] = new RussWire();
        }
        out = new RussWire();
    }

    /**
     * Execute the MUX logic: output = in[selected_input]
     * where selected_input is decoded from control[2..0].
     */
    public void execute() {
        boolean c0 = control[0].get();
        boolean c1 = control[1].get();
        boolean c2 = control[2].get();

        // Inverted control signals
        boolean c0n = !c0;
        boolean c1n = !c1;
        boolean c2n = !c2;

        // Decode control to enable exactly one input
        boolean s0 = c0n && c1n && c2n && in[0].get();
        boolean s1 = c0  && c1n && c2n && in[1].get();
        boolean s2 = c0n && c1  && c2n && in[2].get();
        boolean s3 = c0  && c1  && c2n && in[3].get();
        boolean s4 = c0n && c1n && c2  && in[4].get();
        boolean s5 = c0  && c1n && c2  && in[5].get();
        boolean s6 = c0n && c1  && c2  && in[6].get();
        boolean s7 = c0  && c1  && c2  && in[7].get();

        out.set(s0 || s1 || s2 || s3 || s4 || s5 || s6 || s7);
    }
}
