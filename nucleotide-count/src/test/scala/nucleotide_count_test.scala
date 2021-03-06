import org.scalatest._

class NucleotideCountSpecs extends FlatSpec with Matchers {
  "empty dna string" should "have no adenosine" in {
    new DNA("").count('A') should be (0)
  }

  it should "have no nucleotides" in {
    
    val expected = Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 0)
    new DNA("").nucleotideCounts should be (expected)
  }

  "a repetitive sequence" should "count cytidine" in {
    
    new DNA("CCCCC").count('C') should be (5)
  }

  it should "have only guanosine" in {
    
    val expected = Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 8)
    new DNA("GGGGGGGG").nucleotideCounts should be (expected)
  }

  "a mixed dna string" should "count only thymidine" in {
    
    new DNA("GGGGGTAACCCGG").count('T') should be (1)
  }

  it should "count a nucleotide only once" in {
    
    val dna = new DNA("CGATTGGG")
    dna.count('T')
    dna.count('T') should be (2)
  }

  it should "not change counts after counting adenosine" in {
    
    val dna = new DNA("GATTACA")
    dna.count('A')
    val expected = Map('A' -> 3, 'T' -> 2, 'C' -> 1, 'G' -> 1)
    dna.nucleotideCounts should be (expected)
  }

  it should "validate nucleotides" in {
    
    evaluating {
      new DNA("GACT").count('X')
    } should produce [IllegalArgumentException]
  }

  it should "validate dna not rna" in {
    
    evaluating {
      new DNA("ACGU")
    } should produce [IllegalArgumentException]
  }

  it should "validate dna" in {
    
    evaluating {
      new DNA("John")
    } should produce [IllegalArgumentException]
  }

  it should "count all nucleotides" in {
    
    val s = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"
    val dna = new DNA(s)
    val expected = Map('A' -> 20, 'T' -> 21, 'G' -> 17, 'C' -> 12)
    dna.nucleotideCounts should be (expected)
  }
}
