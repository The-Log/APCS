/*
  * This node stores two values.
  * The compareTo method must ensure that the lowest frequency has the highest priority.
  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
    private Object value;
    private Integer frequency;
    private HuffmanTreeNode left, right;

    public HuffmanTreeNode(Object initValue, Integer freq)
    {
        value = initValue;
        frequency = freq;
        left = null;
        right = null;
    }

    public HuffmanTreeNode( Object initValue, Integer freq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
    {
        value = initValue;
        frequency = freq;
        left = initLeft;
        right = initRight;
    }

    public Integer getFrequency()
    {
        return frequency;
    }

    public Object getValue() { return value; }

    public HuffmanTreeNode getLeft()
    {
        return left;
    }

    public HuffmanTreeNode getRight()
    {
        return right;
    }

    public void setFrequency(Integer theNewFrequency)
    {
        frequency = theNewFrequency;
    }

    public void setValue(Object theNewValue)
    {
        value = theNewValue;
    }

    public void setLeft(HuffmanTreeNode theNewLeft)
    {
        left = theNewLeft;
    }

    public void setRight(HuffmanTreeNode theNewRight)
    {
        right = theNewRight;
    }

    public int compareTo(HuffmanTreeNode o) {
        if(o.frequency < frequency)
            return 1;
        else if (o.frequency > frequency)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        String s =  value + " : " +frequency;
        if(right !=  null)
            s += " Right Child: " + right;
        if(left != null)
            s +=" Left Child: " + left;
        return s;
    }
}
