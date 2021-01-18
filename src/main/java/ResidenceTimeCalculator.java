import com.perfdynamics.pdq.*;

public class ResidenceTimeCalculator {
    private final static String NAME = "Network";
    private final static String WORK_NAME = "Work";
    private final static String NODE_PREFIX = "n";
    private final double[] servingTimes;
    private final double[] visitCounts;
    private final int componentCount;
    private final PDQ pdq;

    public ResidenceTimeCalculator(double[] servingTimes, double[] visitCounts) {
        this.servingTimes = servingTimes;
        this.visitCounts = visitCounts;
        this.componentCount = Math.min(servingTimes.length, visitCounts.length);
        this.pdq = new PDQ();
    }

    public void calculate(double lambda) {
        pdq.Init(NAME);
        pdq.CreateOpen(WORK_NAME, lambda);

        for (int i = 0; i < componentCount; i++) {
            pdq.CreateNode(NODE_PREFIX + (i + 1), Node.CEN, QDiscipline.FCFS);
        }

        for (int i = 0; i < componentCount; i++) {
            pdq.SetVisits(NODE_PREFIX + (i + 1), WORK_NAME, visitCounts[i], servingTimes[i]);
        }

        pdq.Solve(Methods.CANON);
    }

    public double getResidenceTime(int componentIndex) {
        return pdq.GetResidenceTime(NODE_PREFIX + componentIndex, WORK_NAME, Job.TRANS);
    }

    public double getTotalResidenceTime() {
        return pdq.GetResponse(Job.TRANS, WORK_NAME);
    }
}
